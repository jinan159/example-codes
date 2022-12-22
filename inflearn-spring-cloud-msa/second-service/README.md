# second-service

API Gateway 학습용 Microservice

## 의존성 버전
> 강좌에서 설명하는 버전과 다르다는 점 참고해주시기 바랍니다.

|      항목      |    강좌    | 이 프로젝트 |
|:------------:|:--------:|:------:|
| Spring Boot  |  2.3.8   | 2.7.6  |

- `Spring Boot 2.7.6`
  - 강의에서는 2.3.8 로 하지만, 이 프로젝트는 굳이 2.3.x 를 쓰지 않아도됨
  - 그래서 현재(2022-12-22) OSS Support 를 제공하는 가장 안정적인 버전인 2.7.6 선택

## 실행 방법

```shell
# Maven
./mvnw spring-boot:run

# Java
java -jar ./target/second-service-0.0.1-SNAPSHOT.jar
```