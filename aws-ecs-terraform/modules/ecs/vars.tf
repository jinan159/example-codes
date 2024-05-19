# common variables --------
variable "app-env" {
  type = string
}

variable "service-name" {
  type = string
}

variable "app-name" {
  type = string
}

variable "domain" {
  type = string
}

variable "region" {
  type    = string
  default = "ap-northeast-2"
}

variable "vpc-id" {
  type = string
}

variable "public-subnet-ids" {
  type = list(string)
}

variable "private-subnet-ids" {
  type = list(string)
}

variable "route53-zone-id" {
  type = string
}

variable "certificate_arn" {
  type = string
}

# ecs variables --------
variable "host-port" {
  type = number
}

variable "container-port" {
  type = number
}

variable "cpu" {
  type    = number
  default = 512
}

variable "memory" {
  type    = number
  default = 1024
}

variable "desired-count" {
  type    = number
  default = 1
}

variable "lb-ingress" {
  type = list(
    object({
      protocol = string
      port     = number
      cidr     = string
    })
  )
}

variable "lb-private" {
  type = bool
  default = true
}

variable "lb-deregistration_delay" {
  type = number
  default = 30
}

variable "lb-health-check" {
  type = object({

    interval = number
    path = string
    timeout = number
    matcher = string
    healthy_threshold = number
    unhealthy_threshold = number
  })
  default = {
    deregistration_delay  = 20
    interval = 15
    path = "/health"
    timeout = 10
    matcher = "200"
    healthy_threshold = 3
    unhealthy_threshold = 2
  }
}