# Fetch the latest AL2 AMI
data "aws_ami" "amazon_linux_2" {
  most_recent = true

  filter {
    name   = "owner-alias"
    values = ["amazon"]
  }

  filter {
    name   = "name"
    values = ["amzn2-ami-hvm*"]
  }
}

# Define a key pair for SSH access
resource "aws_key_pair" "shycat_key" {
  key_name   = "shycat-key"
  public_key = file("~/.ssh/shycat-key.pub")
}

# Security group allowing SSH and application access
resource "aws_security_group" "shycat_sg" {
  name_prefix = "shycat-sg"
  vpc_id      = aws_vpc.app_vpc.id

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 8080
    to_port     = 8080
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Create an EC2 instance for the Shycat backend
resource "aws_instance" "shycat_instance" {
  ami                    = data.aws_ami.amazon_linux_2.id
  instance_type          = "t2.micro"
  vpc_security_group_ids = [aws_security_group.shycat_sg.id]
  subnet_id              = aws_subnet.public_a.id

  key_name = aws_key_pair.shycat_key.key_name

  user_data = <<-EOF
    #!/bin/bash
    # Update system and install Java 21
    sudo yum update -y
    sudo rpm --import https://yum.corretto.aws/corretto.key
    sudo curl -L -o /etc/yum.repos.d/corretto.repo https://yum.corretto.aws/corretto.repo
    sudo yum install -y java-21-amazon-corretto-devel

    # Create a directory for the Shycat Backend Application
    sudo mkdir -p /opt/shycat
    sudo chown ec2-user:ec2-user /opt/shycat

    # Create a systemd service file for the Shycat application
    sudo tee /etc/systemd/system/shycat.service > /dev/null <<EOL
    [Unit]
    Description=Shycat Backend Application
    After=network.target

    [Service]
    User=ec2-user
    ExecStart=/usr/bin/java -jar /opt/shycat/shycat.jar
    Restart=always
    StandardOutput=journal
    StandardError=journal

    [Install]
    WantedBy=multi-user.target
    EOL

    # Reload systemd and enable the Shycat service
    sudo systemctl daemon-reload
    sudo systemctl enable shycat

    echo "Shycat setup complete. Deploy your shycat.jar to /opt/shycat/"
  EOF

  tags = {
    Name = "ShycatBackend"
  }
}

# Create an Elastic IP and associate it with the EC2 instance
resource "aws_eip" "shycat_eip" {
  instance = aws_instance.shycat_instance.id

  tags = {
    Name = "ShycatElasticIP"
  }
}

# Create an ACM certificate for api.shycat.fyi with DNS validation
resource "aws_acm_certificate" "shycat_api_cert" {
  domain_name       = "api.shycat.fyi"
  validation_method = "DNS"

  tags = {
    Name = "shycat-api-cert"
  }
}

# Create DNS validation records for ACM certificate (for external validation)
resource "aws_acm_certificate_validation" "shycat_api_cert_validation" {
  certificate_arn = aws_acm_certificate.shycat_api_cert.arn

  validation_record_fqdns = [
    for option in aws_acm_certificate.shycat_api_cert.domain_validation_options : option.resource_record_name
  ]
}

# Output the validation information for manual setup
output "certificate_validation_options" {
  value = aws_acm_certificate.shycat_api_cert.domain_validation_options
}

# ALB Target Group for the EC2 instance
resource "aws_lb_target_group" "api_tg" {
  name        = "${var.app_name}-api"
  port        = var.api_port
  protocol    = "HTTP"
  target_type = "instance"
  vpc_id      = aws_vpc.app_vpc.id

  health_check {
    enabled             = true
    path                = "/health"
    port                = 8080
    healthy_threshold   = 10
    unhealthy_threshold = 10
  }

  depends_on = [aws_alb.api_lb]
}

# Application Load Balancer (ALB)
resource "aws_alb" "api_lb" {
  name               = "${var.app_name}-api-lb"
  internal           = false
  load_balancer_type = "application"

  subnets = [
    aws_subnet.public_a.id,
    aws_subnet.public_b.id,
  ]

  security_groups = [
    aws_security_group.http.id,
    aws_security_group.https.id,
    aws_security_group.egress_all.id,
  ]

  depends_on = [aws_internet_gateway.igw]
}

# ALB Listener for HTTPS traffic
resource "aws_alb_listener" "api_https" {
  load_balancer_arn = aws_alb.api_lb.arn
  port              = 443
  protocol          = "HTTPS"
  ssl_policy        = "ELBSecurityPolicy-TLS13-1-2-2021-06"
  certificate_arn   = aws_acm_certificate.shycat_api_cert.arn

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.api_tg.arn
  }
}
