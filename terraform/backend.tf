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

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Create EC2 instance for Shycat backend
resource "aws_instance" "shycat_instance" {
  ami           = data.aws_ami.amazon_linux_2.id
  instance_type = "t2.micro"

  key_name        = aws_key_pair.shycat_key.key_name
  security_groups = [aws_security_group.shycat_sg.name]

  user_data = <<-EOF
    #!/bin/bash
    # Update system and install Java 21
    sudo yum update -y
    sudo amazon-linux-extras enable corretto21
    sudo yum install -y java-21-amazon-corretto

    # Create a directory for the Shycat Spring Boot application
    sudo mkdir -p /opt/shycat
    sudo chown ec2-user:ec2-user /opt/shycat

    # Create a systemd service file for the Shycat application
    sudo tee /etc/systemd/system/shycat.service > /dev/null <<EOL
    [Unit]
    Description=Shycat Spring Boot Application
    After=network.target

    [Service]
    User=ec2-user
    ExecStart=/usr/bin/java -jar /opt/shycat/app.jar
    Restart=always
    StandardOutput=journal
    StandardError=journal

    [Install]
    WantedBy=multi-user.target
    EOL

    # Reload systemd and enable the Shycat service
    sudo systemctl daemon-reload
    sudo systemctl enable shycat

    echo "Shycat setup complete. Deploy your app.jar to /opt/shycat/"
  EOF

  tags = {
    Name = "ShycatBackend"
  }
}
