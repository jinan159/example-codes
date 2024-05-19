resource "aws_security_group" "alb-sg" {
  vpc_id = var.vpc-id
  name = "${local.service-full-name}-${local.lb-suffix}-sg"

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port         = 443
    protocol          = "tcp"
    to_port           = 443
    cidr_blocks       = ["0.0.0.0/0"]
  }

  dynamic "ingress" {
    for_each = var.lb-ingress
    content {
      from_port       = ingress.value.port
      protocol        = ingress.value.protocol
      to_port         = ingress.value.port
      cidr_blocks     = [ingress.value.cidr]
    }
  }

  egress {
    from_port = 0
    protocol  = "-1"
    to_port   = 0
    cidr_blocks = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }
}