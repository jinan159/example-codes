FROM amazoncorretto:17

RUN mkdir /app

RUN yum install -y wget tar

COPY ./build/libs/sikdorak-*.jar /app/sikdorak.jar
COPY ./docker/sikdorak-was/run.sh /app/run.sh

ENTRYPOINT ["/app/run.sh"]