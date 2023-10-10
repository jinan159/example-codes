FROM amazoncorretto:17

RUN mkdir /app

WORKDIR /app

COPY ./build/libs/*-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]
