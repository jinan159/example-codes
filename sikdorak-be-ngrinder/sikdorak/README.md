# sikdorak-be-ngrinder

[식도락 서비스](https://github.com/jjik-muk/sikdorak) 를 기반으로 ngrinder 실습을 하기위한 프로젝트입니다.

# 1. 실행 방법

> 실행 전 controller war 파일을 다운로드하여 `ngrinder/controller/ngrinder-controller-3.5.8.war` 경로로 이동해주세요. <br>
> 다운로드 링크 : https://github.com/naver/ngrinder/releases/tag/ngrinder-3.5.8-20221230

다음 명령어로 실습 환경을 바로 실행할 수 있습니다.

```shell
# 이미지 빌드
docker-compose build

# 컨테이너들 전체 실행
docker-compose up -d

# 컨테이너들 전체 종료
docker-compose down
```

만약 agent 의 개수를 조정하고 싶다면 다음과 같이 하실 수 있습니다.

```shell
# 컨테이너들 전체 실행(agent 는 2개 실행)
docker-compose up -d --scale agent=2

# 실행중인 상태(docker-compose up 한 상태)에서 agent 개수 조정
# 1개 였다면, 1개 더 실행해서 총 2개의 agent 컨테이너가 controller 에 연결됨
# 3개 였다면, 1개가 종료되어 2개의 agent 컨테이너만 유지함
docker-compose scale agent=2

# 컨테이너들 전체 종료 후 컨테이너 삭제
docker-compose down
```

# 2. 상세 설명

<img width="654" alt="image" src="https://user-images.githubusercontent.com/45728407/212480142-a9dd0b65-b91e-4344-b4bc-ba474eb8e83e.png">

nGrinder 아키텍처는 Controller, Agent, Target server 로 이루어집니다. <br>

- Controller
  - 사용자 인터페이스 제공(관리 페이지)
  - 테스트 수행시 agent 에게 테스트 수행 명령
- Agent(Agent Controller)
  - controller 와 독립적으로 수행됨
  - controller 와 연결을 유지하며 명령을 대기
  - 테스트 수행 명령을 받으면 Target server 로 요청 전송
- Target server
  - 부하 테스트를 받는 대상

이 구조를 이 프로젝트에서는 Docker, Docker compose 를 이용하여 다음과 같이 구성했습니다.

```text
sikdorak-br-ngrinder(ROOT)
├─ Dockerfile : Target server(식도락 서비스 API 서버)
└─ docker-compose.yml : Target server, DB, Controller, Agent 구성을 설정
└─ ngrinder
    └─ controller
        └─ Dockerfile : Controller 컨테이너 이미지 정의
```

## 2.1 nGrinder Controller & Agent

<img width="827" alt="image" src="https://user-images.githubusercontent.com/45728407/212456696-01dafe6a-a07e-4871-8a73-8aac1546e8c9.png">

위 그림은 controller 와 ngrinder/agent 간의 연결 과정이고 이를 자세히 설명하면 다음과 같습니다. <br>

1. controller 컨테이너가 완전히 실행 완료된 상태가 됨
2. agent 실행 파일을 controller 로 부터 다운로드 시도(최대 30번)
    - 원래 agent 실행 파일은 controller 로 부터 다운로드 받게 되어있음
    - 그래서 ngrinder/agent 이미지는 agent 실행 파일이 없고, controller 로 부터 다운로드 받음
    - [ngrinder/agent 컨테이너 실행 스크립트](https://github.com/naver/ngrinder/blob/develop/docker/agent/scripts/run.sh)에 따르면 최대 30번만 다운로드 시도를 함
3. 다운로드한 agent 파일을 실행하여 controller 와 연결

> **⚠️ 주의할 점**
> 
> ngrinder/agent 에서 controller 의 host 정보는 controller:80 으로 하드코딩 되어있습니다. <br>
> (위 그림에서의 1번 요청은 `wget http://controller:80/agent/download` 이런 형태임)<br>
> 그래서 ngrinder/agent 이미지와 함께 실행하려면 다음 사항을 숙지해야 합니다. <br>
> 1. ngrinder controller 컨테이너의 이름은 반드시 `controller` 이어야 함 <br> 
> 2. ngrinder controller 프로젝트는 80번 포트로 실행되어야 함 <br>

### 1) controller

nGrinder 의 [ngrinder/controller](https://hub.docker.com/r/ngrinder/controller) 이미지는 초기 실행시간이 너무 많이 걸려서 직접 컨테이너를 구성했습니다. <br>
(nGrinder 의 버전은 [3.5.8 버전](https://github.com/naver/ngrinder/releases/tag/ngrinder-3.5.8-20221230) 을 사용합니다.)

ngrinder 는 [3.5.1 버전](https://github.com/naver/ngrinder/releases/tag/ngrinder-3.5.1-20200831) 부터 java11 을 지원하기 때문에 11 버전을 기반으로 구성했습니다. <br>
그리고 controller 에서는 groovy script 실행을 위해 maven 과 gradle 을 필요로 합니다. <br>
그래서 gradle 기반 이미지에서 maven 을 추가로 설치해서 실행 환경을 구성했습니다.

```dockerfile
FROM gradle:7-jdk11

RUN apt-get update \
    && apt-get install -y maven

RUN mkdir ~/app && \
    mkdir ~/app/controller-home

COPY ngrinder-controller-3.5.8.war ~/app/ngrinder-controller.war

# war 파일의 기본 포트는 8080 이라서, 이를 80 포트로 변경하여 사용
ENTRYPOINT ["java", "-XX:MaxMetaspaceSize=200m", "-jar", "/app/ngrinder-controller.war", "--port", "80"]
```

마지막으로 controller 에 healthchech 설정을 추가해야합니다. <br>

ngrinder/agent 컨테이너 실행 스크립트에서는 controller 에 agent 실행 파일다운로드 요청을 합니다. <br>
하지만 controller 가 아직 초기화중이라면 agent 컨테이너는 요청에 실패하고 종료될 가능성이 있습니다. <br>

그래서 controller 에 healthcheck 를 추가하고, agent 컨테이너는 service healthy 확인 후 실행되게 합니다.

```yml
controller:
  ...
  healthcheck:
    test: ["CMD", "curl", "-f", "http://localhost"]
    interval: 10s
    timeout: 5s
    retries: 4

agent:
  ...
  depends_on:
    controller:
      condition: service_healthy
```

### 2) agent

agent 는 [ngrinder/agent](https://hub.docker.com/r/ngrinder/agent) 이미지를 그대로 사용합니다.<br>

```yml
agent:
image: ngrinder/agent:3.5.8
depends_on:
  controller:
    condition: service_healthy
```

agent 컨테이너 설정에서 신경쓸 것은 두 가지 입니다.

1. controller 의 healthcheck 결과(위에서 설명 함)
2. container_name 을 지정하지 않는 것

```shell
docker-compose up -d --scale agent=3
```

container_name 에 이름을 지정하면 scale 로 여러개를 실행하는 경우 이름 중복으로 실행되지 않습니다. <br>
그래서 이름을 따로 설정하지않고 두면, docker-compose 가 규칙에 따라 컨테이너 이름을 붙여줍니다.

- docker-compose 컨테이너 명명 규칙 : `<current directory name>_<service name>_<sequence>`
  - ex) sikdorak-be-ngrinder_agent_1

## 2.2 Target server

실제로 부하 테스트를 받는 대상에 대한 설정입니다. <br>
이 컨테이너들은 AWS Free tier 기준으로 리소스가 제한되어 있습니다.

### 1) sikdorak-was

식도락 서비스 API 서버로 부하테스트 타겟이 되는 컨테이너입니다. <br>
API 문서 : http://localhost:8080/docs/index.html <br>

이 컨테이너의 리소스 제한은 AWS EC2 의 [t3.micro](https://aws.amazon.com/ko/ec2/instance-types/) 기준으로 잡았습니다. <br>

```yml
deploy:
  resources:
    limits:
      cpus: '2'
      memory: 1024M
    reservations:
      memory: 512M
```

### 2) sikdorak-db

식도락 서비스의 DB 서버입니다. <br>
이 컨테이너의 리소스 제한은 AWS RDS 의 [db.t3.micro](https://aws.amazon.com/ko/ec2/instance-types/) 기준으로 잡았습니다. <br>

```yml
deploy:
  resources:
    limits:
      cpus: '2'
      memory: 1024M
    reservations:
      memory: 512M
```
