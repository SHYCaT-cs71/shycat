# Terraform common resources

# Terraform Configuration
terraform {
  backend "s3" {
    bucket  = "shycat-tf-state"
    key     = "terraform.tfstate"
    region  = "us-east-1"
    encrypt = true
  }
}

# AWS Provider Configuration
provider "aws" {
  region = var.aws_region
}

# AWS Caller Identity
data "aws_caller_identity" "aws" {}

# Local Values
locals {
  account_id = data.aws_caller_identity.aws.account_id
}
