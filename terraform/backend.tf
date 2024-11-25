
# Latest AL2 AMI
data "aws_ami" "amazon-linux-2" {
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

# Create a security group allowing HTTP and custom communication
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

# Create an EC2 instance
resource "aws_instance" "shycat_instance" {
  ami           = data.aws_ami.amazon-linux-2.id
  instance_type = "t2.micro"

  key_name      = aws_key_pair.shycat_key.key_name
  security_groups = [aws_security_group.shycat_sg.name]

  user_data = <<-EOF
    #!/bin/bash
    set -e

    sudo yum update -y
    sudo rpm --import https://yum.corretto.aws/corretto.key
    sudo curl -o /etc/yum.repos.d/corretto.repo https://yum.corretto.aws/corretto.repo

    # Install Java 21
    sudo yum install -y java-21-amazon-corretto-devel
    java -version
  EOF

  tags = {
    Name = "shycat-backend"
  }
}
