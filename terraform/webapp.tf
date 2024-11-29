# Terraform resources for WebApp

resource "aws_amplify_app" "shycat_webapp" {
  name       = var.app_name
  repository = "https://github.com/SHYCaT-cs71/shycat"

  access_token = var.github_access_token

  environment_variables = {
    API_ENDPOINT              = "http://${aws_instance.shycat_instance.public_dns}:8080"
    AMPLIFY_MONOREPO_APP_ROOT = "webapp/shycat"
  }
}

resource "aws_amplify_branch" "shycat_webapp_main" {
  app_id      = aws_amplify_app.shycat_webapp.id
  branch_name = "main"

  framework = "React"
  stage     = "PRODUCTION"

  enable_auto_build = true

  environment_variables = {
    API_ENDPOINT              = "http://${aws_instance.shycat_instance.public_dns}:8080"
    AMPLIFY_MONOREPO_APP_ROOT = "webapp/shycat"
  }
}

# resource "aws_amplify_branch" "shycat_webapp_dev" {
#   app_id     = aws_amplify_app.shycat_webapp.id
#   branch_name = "branch-here"

#   framework = "React"
#   stage     = "DEVELOPMENT"

#   environment_variables = {
#     API_ENDPOINT = "http://${aws_instance.shycat_instance.public_dns}:8080"
#     AMPLIFY_MONOREPO_APP_ROOT = "webapp/shycat"
#   }
# }

# resource "aws_amplify_domain_association" "webapp_domain" {
#   app_id      = aws_amplify_app.shycat_webapp.id
#   domain_name = "shycat.fyi"

#   sub_domain {
#     branch_name = aws_amplify_branch.shycat_webapp_main.branch_name
#     prefix      = ""
#   }

#   # sub_domain {
#   #   branch_name = aws_amplify_branch.shycat_webapp_dev.branch_name
#   #   prefix      = "dev."
#   # }
# }

# Generate the .env file using local-exec
resource "null_resource" "generate_env_file" {
  triggers = {
    always_run = timestamp()
  }

  provisioner "local-exec" {
    command = <<EOT
      rm -rf ../webapp/shycat/.env
      echo "FAST_REFRESH=false" >> ../webapp/shycat/.env
      echo "REACT_APP_API_URL=http://${aws_instance.shycat_instance.public_dns}:8080" >> ../webapp/shycat/.env
    EOT
  }
}
