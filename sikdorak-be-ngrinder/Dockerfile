FROM amazoncorretto:17

RUN mkdir /app

COPY ./build/libs/sikdorak-*.jar /app/sikdorak.jar

ENTRYPOINT java -jar /app/sikdorak.jar -Xms512m -Xmx1024m