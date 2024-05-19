data "aws_acm_certificate" "jinan159-test-com-cert" {
  domain = "jinan159-test.com"
}

output "jinan159-test-com-cert-arn" {
  value = data.aws_acm_certificate.jinan159-test-com-cert.arn
}
