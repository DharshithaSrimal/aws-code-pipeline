resource "aws_s3_bucket" "example_bucket" {
  bucket = "website-pipeline-repo-terraform"
  acl    = "private" # Access Control List (ACL) for the bucket
}