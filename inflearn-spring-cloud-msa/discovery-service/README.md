# discovery-service

> 강좌에서 설명하는 버전과 다르다는 점 참고해주시기 바랍니다.

|      항목      |    강좌    |  이 프로젝트  |
|:------------:|:--------:|:--------:|
| Spring Boot  |  2.4.2   |  2.7.6   |
| Spring Cloud | 2020.0.0 | 2021.0.5 |

- `Spring Boot 2.7.6` : 현재 OSS Support 를 제공하는 가장 안정적인 버전이라서 선택
  ![](https://user-images.githubusercontent.com/45728407/208869510-a6e5c72d-a18c-4950-a489-6a8d8f0fe581.png)

- `Spring Cloud 21.0.5`
    - Spring Cloud 는 Spring Boot 와 버전을 맞춰야 하는데, 위에서 선택한 `2.7.x` 버전은 `2021.0.x` 버전을 선택해야 함
      ![image](https://user-images.githubusercontent.com/45728407/208875315-2918a587-4915-4751-ba4a-ddb8a9194428.png)
    - 현재 Spring Cloud 의 `2021.0.x` 버전 중 가장 안정적인 버전인 `21.0.5` 선택
      ![image](https://user-images.githubusercontent.com/45728407/208875329-bffb9707-b5c2-40d0-9e78-d07f18a351e2.png)

Service Discovery 역할을 하는 서버

실행 방법

```shell
# Maven
cd ./discovery-service
./mvnw spring-boot:run

# Java
java -jar ./target/discovery-service-0.0.1-SNAPSHOT.jar
```