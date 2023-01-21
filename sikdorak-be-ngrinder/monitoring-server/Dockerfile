FROM amazoncorretto:17

RUN mkdir /app

COPY ./build/libs/monitoring-server-*.jar /app/monitoring-server.jar

ENTRYPOINT ["java", "-jar", "/app/monitoring-server.jar"]
