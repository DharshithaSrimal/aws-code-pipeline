resource "aws_s3_bucket" "website_bucket" {
  bucket = "pipeline-artifact-terraform-pipeline"
  acl    = "private" # Access Control List (ACL) for the bucket
}