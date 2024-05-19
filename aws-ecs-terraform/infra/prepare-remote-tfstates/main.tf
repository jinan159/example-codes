module "provider" {
  source = "../../modules/provider"
}

resource "aws_dynamodb_table" "tfstates-lock" {
  name = "glorang-psyt-terraform-tfstates-lock"
  read_capacity = 5
  write_capacity = 5
  hash_key = "LockID"

  attribute {
    name = "LockID"
    type = "S"
  }
}

resource "aws_s3_bucket" "logs" {
  bucket = "glorang-psyt.terraform-logs"

  tags = {
    Infra = "glorang"
    Name = "terraform-logs"
  }
}

resource "aws_s3_bucket_acl" "logs-acl" {
  bucket = aws_s3_bucket.logs.id
  acl = "log-delivery-write"
}

resource "aws_s3_bucket" "tfstates" {
  bucket = "glorang-psyt.terraform-tfstates"

  tags = {
    Infra = "glorang"
    Name = "terraform-tfstates"
  }

  lifecycle {
    prevent_destroy = true
  }
}

resource "aws_s3_bucket_acl" "tfstates-acl" {
  bucket = aws_s3_bucket.tfstates.id
  acl = "private"
}

resource "aws_s3_bucket_versioning" "tfstates-versioning" {
  bucket = aws_s3_bucket.tfstates.id

  versioning_configuration {
    status = "Enabled"
  }
}

resource "aws_s3_bucket_logging" "tfstates-logging" {
  bucket = aws_s3_bucket.tfstates.id
  target_bucket = aws_s3_bucket.logs.id
  target_prefix = "log/"
}