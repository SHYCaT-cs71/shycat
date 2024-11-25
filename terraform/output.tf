# Terraform Outputs

output "shycat_backend_url" {
  value       = "http://${aws_instance.shycat_instance.public_dns}:8080"
  description = "Public DNS of the Shycat backend server"
}
