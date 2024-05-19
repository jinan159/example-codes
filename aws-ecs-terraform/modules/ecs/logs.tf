resource "aws_cloudwatch_log_group" "log_group" {
  name = "/${var.service-name}/${var.app-name}/${var.app-env}"
}
