resource "aws_iam_policy" "developer_policy" {
  name        = "developer_policy"
  path        = "/system/"
  description = "developer policy"

  policy = <<EOF
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Action": [
                "s3:ListAllMyBuckets"
            ],
            "Effect": "Allow",
            "Resource": "*"
        }
    ]
}
EOF
}