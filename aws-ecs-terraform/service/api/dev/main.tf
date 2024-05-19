module "provider" {
  source = "../../../modules/provider"
}

module "vpc" {
  source = "../../../modules/vpc"
}

module "route53" {
  source = "../../../modules/route53"
}

module "acm" {
  source = "../../../modules/acm"
}

module "ecs" {
  source = "../../../modules/ecs"

  app-env           = "dev"
  service-name      = "service"
  app-name          = "api"
  domain            = "dev-service-api"
  vpc-id            = module.vpc.vpc-id
  public-subnet-ids = [
    module.vpc.subnet-public1-id,
    module.vpc.subnet-public2-id
  ]
  private-subnet-ids = [
    module.vpc.subnet-private1-id,
    module.vpc.subnet-private2-id
  ]

  route53-zone-id = module.route53.private-jinan159-test-com-zone-id
  certificate_arn = module.acm.jinan159-test-com-cert-arn
  host-port       = 8080
  container-port  = 8080
  cpu             = 1024
  memory          = 2048
  desired-count   = 1
  lb-ingress      = [
    {
      protocol = "tpc"
      port     = 80
      cidr     = "10.255.0.0/16"
    },
    {
      protocol = "tpc"
      port     = 443
      cidr     = "10.255.0.0/16"
    }
  ]
  lb-private              = true
}
