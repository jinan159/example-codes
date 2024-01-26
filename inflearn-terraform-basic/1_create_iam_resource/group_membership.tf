resource "aws_iam_group_membership" "developer_membership" {
  name = "developer_membership"
  users = [
    aws_iam_user.developer_jwkim.name
  ]
  group = aws_iam_group.developer_group.name
}