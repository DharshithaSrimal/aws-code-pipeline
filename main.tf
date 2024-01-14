data "aws_codecommit_repository" "repo" {
  repository_name = var.repo_name
}

resource "aws_codepipeline" "example" {
  name = "terraform-pipeline"

  role_arn = aws_iam_role.codepipeline_role.arn

  artifact_store {
    location = aws_s3_bucket.website_bucket.id
    type     = "S3"
  }

  stage {
    name = "Source"

    action {
      name     = "SourceAction"
      category = "Source"
      owner    = "AWS"
      provider = "CodeCommit"
      version  = "1"
      configuration = {
        RepositoryName = var.repo_name
        BranchName     = "main"
      }

      output_artifacts = ["source_artifact"]
    }
  }

  stage {
    name = "Deploy"

    action {
      name            = "DeployAction"
      category        = "Deploy"
      owner           = "AWS"
      provider        = "S3"
      version         = "1"
      input_artifacts = ["source_artifact"]
      configuration = {
        BucketName      = aws_s3_bucket.website_bucket.bucket
        Extract         = "false"
        ObjectKey       = "index.html"  # Update this to the path of your main HTML file
        CacheControl    = "max-age=0, must-revalidate"
      }
      run_order = 1
    }
  }
}
