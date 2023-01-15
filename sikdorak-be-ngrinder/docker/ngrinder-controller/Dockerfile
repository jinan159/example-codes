FROM gradle:7-jdk11

RUN apt-get update \
    && apt-get install -y maven

RUN mkdir ~/app && \
    mkdir ~/app/controller-home

COPY ngrinder-controller-3.5.8.war ~/app/ngrinder-controller.war

ENTRYPOINT ["java", \
"-XX:MaxMetaspaceSize=200m", \
"-Djava.io.tmpdir=${NGRINDER_HOME}", \
"-jar", "~/app/ngrinder-controller.war", \
"--port", "80" \
]
