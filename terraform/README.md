# SHYCaT

## Directory Overview
- **api:** Contains the code for the API used as a bridge between the web app and RDS.
- **terraform:** Contains IaC for infrastructure to deploy the application.
- **webapp:** Contains the code for the web app.

## Infrastructure

* **AWS EC2**: AWS EC2 is sued to host our API service

* **AWS RDS**: AWS RDS is used to host our database

* **AWS Amplify**: AWS Amplify is used to host our web app

* **AWS Route53**: AWS Route53 is used to handle DNS for our service

## Installation Manual

### Prerequisites

Before proceeding, ensure you have the following prerequisites installed:

- [Terraform](https://www.terraform.io/downloads.html) (version 0.12 or higher)
- [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html) configured with appropriate credentials
- Text editor (e.g., Visual Studio Code, Sublime Text)

### Terraform Deployment Guide

This guide provides step-by-step instructions for deploying infrastructure using Terraform.

### AWS CLI Setup

1. **Install AWS CLI:**
   If you haven't already installed the AWS CLI, you can download and install it from the [AWS CLI documentation](https://aws.amazon.com/cli/).

2. **Configure AWS CLI:**
   Run the following command and follow the prompts to configure your AWS CLI with the appropriate credentials:
   ```bash
   aws configure
   ```
   You will be prompted to enter your Access Key ID, Secret Access Key, AWS Region, and default output format.

3. **Verify AWS CLI Configuration:**
   After configuring AWS CLI, you can verify the configuration by running the following command:
   ```bash
   aws sts get-caller-identity
   ```
   This command should return details about the AWS account associated with the configured credentials.

### Deployment Steps

1. **Clone Repository:**
   ```bash
   git clone https://github.com/SHYCaT-cs71/shycat.git
   cd shycat
   ```

2. **Initialize Terraform:**
   ```bash
   terraform init
   ```

3. **Plan Deployment:**
   ```bash
   terraform plan
   ```
   This command generates an execution plan that shows what Terraform will do when you apply it.

4. **Apply Configuration:**
   ```bash
   terraform apply
   ```
   Apply the configuration changes to provision infrastructure. Review the execution plan and type `yes` to confirm.

5. **Destroy Infrastructure (Optional):**
    When you're done with the infrastructure, you can destroy it to avoid incurring charges:
    ```bash
    terraform destroy
    ```
    Review the execution plan and type `yes` to confirm.

## Importing Exisitng Terraform resources

To import an existing resource into Terraform state, follow these steps:

1. **Identify Resource:** Determine the resource you want to import. In this example, we'll import an Amplify app and domain association.

4. **Run Import Command:** Use the `terraform import` command to import your existing Amplify resource. The syntax is:

    ```bash
    terraform import <Terraform Resource Name>.<Terraform Resource Identifier> <Resource ID>
    ```

    For example, to import an Amplify app with an `APP_ID` you would run:

    ```bash
    terraform import aws_amplify_app.sentinelrx_webapp APP_ID
    ```
    Replace `APP_ID` with the actual ID of your Amplify app.

    For example, to import an Amplify app domain association you would run:

    ```bash
    terraform import aws_amplify_domain_association.webapp_domain APP_ID/DOMAIN
    ```
    Replace `APP_ID` with the actual ID of your Amplify app and `DOMAIN` with the domain.


5. **Verify State:** After running the import command, Terraform will create a state file (usually `terraform.tfstate`) containing the imported resource's information. Verify that the state file reflects the imported resource correctly.

6. **Manage Resource with Terraform:** Once imported, you can manage the resource using Terraform as usual. Make any necessary changes to your Terraform configuration and apply them with `terraform apply`.

    ```bash
    terraform apply
    ```


## Contributing

To contribute, follow these steps:

1. **Clone Repository:**
   ```bash
   git clone https://github.com/SHYCaT-cs71/shycat.git
   cd shycat
   ```

2. **Create a New Branch:**
   Create a new branch for your changes using the following command:
   ```bash
   git checkout -b feature-branch
   ```
   Replace `feature-branch` with a descriptive name for your branch, such as `fix-bug` or `add-feature`.

4. **Make Changes:**
   Make the necessary changes to the codebase using your preferred text editor or IDE.

5. **Commit Changes:**
   Stage your changes and commit them to your local repository using the following commands:
   ```bash
   git add .
   git commit -m "Your descriptive commit message here"
   ```

6. **Push Changes:**
   Push your changes to your forked repository on GitLab using the following command:
   ```bash
   git push origin feature-branch
   ```
   Replace `feature-branch` with the name of your branch.

7. **Open a Pull Request (PR):**
   Go to the GitHub page of the repository and switch to the branch you just pushed. Click on the "New pull request" button and provide a descriptive title and summary for your changes. Once you're ready, click on the "Create pull request" button to open a new pull request.

8. **Review and Collaborate:**
   Collaborate with project maintainers and other contributors by discussing and iterating on your changes in the pull request. Make any necessary updates based on feedback from reviewers.

9. **Merge Pull Request:**
   Once your pull request is approved and all discussions are resolved, a project maintainer will merge your changes into the main repository. Congratulations on your contribution!
