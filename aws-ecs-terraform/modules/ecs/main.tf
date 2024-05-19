module "acm" {
  source = "../acm"
}

module "route53" {
  source = "../route53"
}

data "aws_vpc" "vpc" {
  id = var.vpc-id
}

locals {
  app-full-name     = "${var.service-name}-${var.app-name}-${var.app-env}"
  service-full-name = "${var.service-name}-${var.app-env}"
  lb-suffix  = (var.lb-private == true) ? "private-lb" : "public-lb"
  lb-subnets = (var.lb-private == true) ? var.private-subnet-ids : var.public-subnet-ids
}
