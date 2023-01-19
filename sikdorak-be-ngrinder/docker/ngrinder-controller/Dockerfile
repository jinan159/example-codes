FROM gradle:7-jdk11

RUN apt-get update \
    && apt-get install -y maven

RUN mkdir /app && \
    mkdir /app/controller-home

COPY ngrinder-controller-3.5.8.war /app/ngrinder-controller.war

ENTRYPOINT ["java", \
"-XX:MaxMetaspaceSize=200m", \
"-jar", "/app/ngrinder-controller.war", \
"--ngrinder-home", "/app/ngrinder-home", \
"--port", "80" \
]
