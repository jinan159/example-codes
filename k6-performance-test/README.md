# k6-performance-test

## k6 설치

https://k6.io/docs/get-started/installation/

## 부하테스트 실행 방법

```shell
# 서비스 빌드
./gradlew build

# 부하테스트에 필요한 이미지 빌드
docker-compose build

# 부하테스트 환경 생성(prometheus, grafana, api)
docker-compose up -d

# 표준 출력으로 실행
k6 run script.js

# prometheus 로 메트릭 출력
K6_PROMETHEUS_RW_SERVER_URL=http://localhost:9090/api/v1/write \
k6 run -o experimental-prometheus-rw script.js
```

## grafana dashboard

- K6 메트릭 대시보드
  - https://grafana.com/grafana/dashboards/19665-k6-prometheus/
- JVM(Micrometer) 대시보드
  - https://grafana.com/grafana/dashboards/4701-jvm-micrometer/

## 적용 방법

1. Prometheus Datasources 추가(이미 추가되어있는 경우 생략)
    - `Menu` > `Connections` > `Data sources` 이동
    - `Add data source` 클릭
    - `Prometheus` 선택
    - HTTP > Prometheus server URL 에 prometheus URL 입력
      - 현재 설정 기준 `http://prometheus:9090` 입력

2. 대시보드 추가
    - `Menu` > `Dashboards` 이동
    - `New`> `Import` 클릭
    - 위 대시보드 URL 을 텍스트 필드에 입력하고, `Load` 클릭

----

**참고 자료**
- [SpringBoot Actuator <--> Prometheus 연동](https://hudi.blog/spring-boot-actuator-prometheus-grafana-set-up/)
- [Docker-compose 로 Prometheus + Grafana 구성하기](https://www.devkuma.com/docs/prometheus/docker-compose-install/)
