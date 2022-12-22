# first-service

API Gateway 학습용 Microservice

## 의존성 버전
> 강좌에서 설명하는 버전과 다르다는 점 참고해주시기 바랍니다.

|      항목      |    강좌    |     이 프로젝트     |
|:------------:|:--------:|:--------------:|
| Spring Boot  |  2.3.8   | 2.3.12.RELEASE |

- `2.3.12.RELEASE`
  - Spring Boot 2.4.x 부터 Netflix Zuul 은 Maintenance mode 가 되어 사용이 불가하다.
  - 그래서 실습을 위해서 2.3.x 으로 버전 다운을 하였고, 그 중에서 가장 최신 버전을 사용함

## 실행 방법

```shell
# Maven
./mvnw spring-boot:run

# Java
java -jar ./target/zuul-service-0.0.1-SNAPSHOT.jar
```