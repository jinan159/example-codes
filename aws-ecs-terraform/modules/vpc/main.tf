data "aws_vpc" "vpc" {
  id = "vpc-id"
}

data "aws_subnet" "public1" {
  id = "public1-subnet-id"
}

data "aws_subnet" "private1" {
  id = "private1-subnet-id"
}

data "aws_subnet" "db1" {
  id = "db1-subnet-id"
}

data "aws_subnet" "public2" {
  id = "public2-subnet-id"
}

data "aws_subnet" "private2" {
  id = "private2-subnet-id"
}

data "aws_subnet" "db2" {
  id = "db2-subnet-id"
}
