# Basic concepts

- 테라폼은 기본적으로 `provider` 가 제공하는 기능들을 사용하는 구조임

# Basic commands

```bash
# init terraform
terraform init

# review the configuration
terraform plan

# apply the configuration
terraform apply

# remove the configuration
terraform destroy
```

## terraform init

1. Backend initialization
- terraform.tfstate 라는 파일이 생성됨
- terraform backend 가 살아있는지 확인(S3 등에 백엔드 스택 정보가 생성되나?)

2. Provider Plugin Download
- provider 플러그인이 있는지 확인 & 로컬에 다운로드

3. Terraform 모듈 다운로드

4. Workspace init
- default workspace 를 만들어줌
  - QA, Staging, Dev, Production 등으로 나눠쓰고 싶을때 사용할 수 있음

5. lock 파일 생성

## variables

적용 우선순위는 5 > ... > 1

1. Variables.tf
2. Environment variables
  - TF_VAR_ 라는 prefix 를 붙여야 함
3. tfvars files
  - terraform.tfvars
  - terraform.tfvars.json
4. Autoloaded tfvars files
  - *.auto.tfvars
  - *.auto.tfvars
5. command line arguments
  - terraform apply -var="asdf=asdf"

## attributes & output

- provider 들은 각자 제공하는 attributes 들이 있음
  - read only 인 경우도 있음
  - 특정 리소스의 id 같은 경우, 생성된 이후에 알 수 있기 때문에 apply 이후 이 id 를 참조할 수 있음

- 그래서 A 에서 B.id 를 참조할 수 있음 = 묵시적 참조
  - depends_on = [ B.id ] 형태로 의존 관계 표현 가능 = 명시적 참조
  - 참조할때는 "${B.id}" 형태로 참조할 수 있

- output 은 attribute 만 내보낼수도, 전체 내용을 json 으로 내보낼 수도 있음
  - 이걸 다른 resource 에서 참조할 수 있음

## state

- 여러 리소스를 만들때마다 states 가 바뀌고 여기에 상태 히스토리가 저장됨

- state 의 목적
  - state 변경 추적
  - 동시 작업 관리
  - 리소스간 의존성 관리(참조 관계 관리)
  - terraform apply 할때마다 상태 저장..?(state Preservation)

- Remote tfstate <- Sync -> Local tfstate
  - apply 할때마다 local, remote tfstate 가 변경됨
    - AWS 의 경우에는 S3 에 저장됨
  - 항상 remote 와 local 을 씽크하려고 하고, 다를 경우 어케되누;;;;;;;;;;;

# Useful commands

```bash
# validate the syntax
terraform validate

# pretty format
terraform fmt

# show all the meta information
terraform show

# show providers
terraform privoders

# show in graph(you need to install graphviz)
terraform graph
terraform graph | dot -Tsvg > sample.svg
```

# Core features

- Mutable resources VS Immutable Resources
  - Mutable resources : EC2 의 Security group 혹은 tag 등
  - Immutable Resources : S3 Bucket name 등

- Life cycle rule
  - resource 들은 lifecycle 이 있고, 이를 컨드롤할 수 있음
    - `create_before_destroy = true` : 기존 리소스를 지우기 전에, 새로 만들고 기존꺼를 지우도록 함
    - `prevent_destroy = false` : 기존 리소스는 지우지 않음(롤백을 위해 남겨두려는 경우)
    - `ignore_changes = ["tags"]` : apply 해도 tag 가 변경되지 않음
      - real infra 에서 바꿔도 인식하지 않음

- Data sources

data 라는 키워드로 기존 리소스(데이터)를 읽어와서 사용할 수 있음

- Meta arguments
  - depends_on : 리소스간 명시적으로 의존성을 설정
  - count : 특정 리소스를 n개 생성하려는 경우 사용
  - for_each : map, set 등을 for-each 루프를 돌면서 생성
  - provider : 특정 프로바이더의 설정을 명시하려는 경우
  - source : 소스가 로컬에 있는지 깃에 있는지?

- Version control
  - 프로바이더의 버전을 명시할 수 있음
