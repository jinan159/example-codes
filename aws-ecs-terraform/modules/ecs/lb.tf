resource "aws_alb" "alb" {
  name               = "${local.app-full-name}-${local.lb-suffix}"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.alb-sg.id]
  subnets            = local.lb-subnets
}

resource "aws_lb_listener" "alb-https-forward" {
  load_balancer_arn = aws_alb.alb.arn
  port              = 443
  protocol          = "HTTPS"
  certificate_arn   = var.certificate_arn
  ssl_policy        = "ELBSecurityPolicy-2016-08"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.alb-tg.arn
  }
}

resource "aws_lb_listener" "alb" {
  load_balancer_arn = aws_alb.alb.arn
  port              = 80
  protocol          = "HTTP"

  default_action {
    type = "redirect"

    redirect {
      port        = 443
      protocol    = "HTTPS"
      status_code = "HTTP_301"
    }
  }
}

resource "aws_lb_target_group" "alb-tg" {
  # TODO var.vpc-id -> data 로 바꾸기
  vpc_id      = var.vpc-id
  name        = "${local.app-full-name}-${local.lb-suffix}-tg"
  port        = var.host-port
  protocol    = "HTTP"
  target_type = "ip"

  deregistration_delay = var.lb-deregistration_delay

  health_check {
    interval            = var.lb-health-check.interval
    path                = var.lb-health-check.path
    timeout             = var.lb-health-check.timeout
    matcher             = var.lb-health-check.matcher
    healthy_threshold   = var.lb-health-check.healthy_threshold
    unhealthy_threshold = var.lb-health-check.unhealthy_threshold
  }

  lifecycle {
    create_before_destroy = true
  }
}
