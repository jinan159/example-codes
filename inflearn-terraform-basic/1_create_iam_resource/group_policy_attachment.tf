resource "aws_iam_group_policy_attachment" "developer_group_policy_attachment" {
  group      = aws_iam_group.developer_group.name
  policy_arn = aws_iam_policy.developer_policy.arn
}