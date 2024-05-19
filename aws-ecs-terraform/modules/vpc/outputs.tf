# vpc ----------
output "vpc-id" {
  value = data.aws_vpc.vpc.id
}

output "vpc-cidr" {
  value = data.aws_vpc.vpc.cidr_block
}

# public1 ----------
output "subnet-public1-id" {
  value = data.aws_subnet.public1.id
}

output "subnet-public1-arn" {
  value = data.aws_subnet.public1.arn
}

output "subnet-public1-cidr" {
  value = data.aws_subnet.public1.cidr_block
}

# private1 ----------
output "subnet-private1-id" {
  value = data.aws_subnet.private1.id
}

output "subnet-private1-arn" {
  value = data.aws_subnet.private1.arn
}

output "subnet-private1-cidr" {
  value = data.aws_subnet.private1.cidr_block
}

# db1 ----------
output "subnet-db1-id" {
  value = data.aws_subnet.db1.id
}

output "subnet-db1-arn" {
  value = data.aws_subnet.db1.arn
}

output "subnet-db1-cidr" {
  value = data.aws_subnet.db1.cidr_block
}

# public2 ----------
output "subnet-public2-id" {
  value = data.aws_subnet.public2.id
}

output "subnet-public2-arn" {
  value = data.aws_subnet.public2.arn
}

output "subnet-public2-cidr" {
  value = data.aws_subnet.public2.cidr_block
}

# private2 ----------
output "subnet-private2-id" {
  value = data.aws_subnet.private2.id
}

output "subnet-private2-arn" {
  value = data.aws_subnet.private2.arn
}

output "subnet-private2-cidr" {
  value = data.aws_subnet.private2.cidr_block
}

# db2 ----------
output "subnet-db2-id" {
  value = data.aws_subnet.db2.id
}

output "subnet-db2-arn" {
  value = data.aws_subnet.db2.arn
}

output "subnet-db2-cidr" {
  value = data.aws_subnet.db2.cidr_block
}
