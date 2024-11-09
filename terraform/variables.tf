# Terraform Variables

variable "app_name" {
  description = "App name"
  type        = string
  default     = "shycat"
}

variable "aws_region" {
  description = "AWS region name"
  type        = string
  default     = "us-east-2"
}

variable "api_port" {
  description = "Port number of api"
  type        = string
  default     = 8080
}
