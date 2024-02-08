resource "aws_s3_bucket" "website_bucket-test" {
  bucket = "website-pipeline-repo-terraform-proj"
  acl    = "private" # Access Control List (ACL) for the bucket

  website {
    index_document = "index.html"
  }
}