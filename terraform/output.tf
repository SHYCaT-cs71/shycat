# Terraform Outputs

output "shycat_backend_url" {
  value       = "https://${aws_instance.shycat_instance.public_dns}"
  description = "Public DNS of the Shycat backend server with HTTPS"
}
