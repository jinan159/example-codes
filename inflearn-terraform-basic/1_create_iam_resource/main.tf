provider "aws" {
  region = "ap-northeast-2"
}

resource "aws_iam_user" "developer_jwkim" {
  name = "developer_jwkim"
  path = "/system/"

  tags = {
    Infra   = "jwkim"
    Service = "jwkim-service"
  }
}

resource "aws_iam_group" "developer_group" {
  name = "developer"
  path = "/system/"
}