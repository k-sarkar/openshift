FROM registry.redhat.io/ubi8/ubi-minimal:8.4-208
USER root
RUN microdnf install java-1.8.0-openjdk-devel -y \
    && microdnf install maven -y \
    && microdnf install tar -y \
    && microdnf install vim -y \
    && touch /tmp/check \
    && echo "health.check=true" >> /tmp/check \
    && echo "readiness.check=true" >> /tmp/check \
    && chmod 777 /tmp/check
ENV STATUS_CHECK_FILE_PATH="/tmp/check"
EXPOSE 8080
COPY src /home/app/src
COPY pom.xml /home/app
RUN mkdir /properties && touch /properties/additional.properties
RUN mvn -f /home/app/pom.xml clean install && cp /home/app/target/*.jar /home/app/application.jar && rm /home/app/target/*.jar
ENTRYPOINT ["java","-jar","/home/app/application.jar"]
