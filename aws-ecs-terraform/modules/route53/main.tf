data "aws_route53_zone" "public-jinan159-test-com" {
  name = "jinan159-test.com"
  private_zone = false
}

data "aws_route53_zone" "private-jinan159-test-com" {
  name = "jinan159-test.com"
  private_zone = true
}

output "public-jinan159-test-com-zone-id" {
  value = data.aws_route53_zone.public-jinan159-test-com.zone_id
}

output "private-jinan159-test-com-zone-id" {
  value = data.aws_route53_zone.private-jinan159-test-com.zone_id
}