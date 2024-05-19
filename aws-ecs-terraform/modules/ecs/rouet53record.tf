resource "aws_route53_record" "alb-record" {
  zone_id = var.route53-zone-id
  type    = "A"
  name    = var.domain

  alias {
    name                   = aws_alb.alb.dns_name
    zone_id                = aws_alb.alb.zone_id
    evaluate_target_health = false
  }
}
