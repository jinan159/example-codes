resource "aws_ecs_cluster" "cluster" {
  name = local.service-full-name
}

resource "aws_ecs_service" "service" {
  name            = "${local.app-full-name}-service"
  cluster         = aws_ecs_cluster.cluster.id
  task_definition = aws_ecs_task_definition.task_definition.arn
  desired_count   = var.desired-count
  launch_type     = "FARGATE"

  network_configuration {
    security_groups = [aws_security_group.ecs_tasks.id]
    subnets         = var.private-subnet-ids

    assign_public_ip = false
  }
}

resource "aws_ecs_task_definition" "task_definition" {
  family                   = local.app-full-name
  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  cpu                      = var.cpu
  memory                   = var.memory

  container_definitions = jsonencode([
    {
      name             = var.app-name
      image            = aws_ecr_repository.repository.repository_url
      essential        = true
      logConfiguration = {
        logDriver = "awslogs"
        options   = {
          awslogs-region        = var.region
          awslogs-stream-prefix = aws_cloudwatch_log_group.log_group.name_prefix
          awslogs-group = aws_cloudwatch_log_group.log_group.name
        }
      }
      portMappings = [
        {
          containerPort = var.container-port
          hostPort      = var.host-port
        },
      ]
    }
  ])
}

resource "aws_security_group" "ecs_tasks" {
  vpc_id = var.vpc-id
  name   = "${local.service-full-name}-ecs-tasks-sg"

  ingress {
    from_port       = 0
    protocol        = "-1"
    to_port         = 0
    security_groups = [
      aws_security_group.alb-sg.id
    ]
  }

  dynamic "ingress" {
    for_each = var.lb-ingress
    content {
      from_port   = ingress.value.port
      protocol    = ingress.value.protocol
      to_port     = ingress.value.port
      cidr_blocks = [ingress.value.cidr]
    }
  }

  egress {
    from_port   = 0
    protocol    = "-1"
    to_port     = 0
    cidr_blocks = ["0.0.0.0/0"]
  }
}
