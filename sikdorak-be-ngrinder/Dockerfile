FROM ubuntu:20.04

RUN mkdir /app

RUN apt update \
    && apt install -y wget tar openjdk-17-jre-headless

COPY ./build/libs/sikdorak-*.jar /app/sikdorak.jar
COPY ./docker/sikdorak-was/run.sh /app/run.sh

ENTRYPOINT ["/app/run.sh"]