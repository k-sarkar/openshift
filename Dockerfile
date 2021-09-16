FROM registry.redhat.io/ubi8/ubi-minimal:8.4-208
USER root
RUN microdnf install java-1.8.0-openjdk-devel -y && microdnf install maven -y &&  microdnf install tar -y
ENV STATUS_CHECK_FILE_PATH="/tmp/check"
RUN touch /home/app/additional.properties && touch /tmp/check && echo "health.check=true" >> /tmp/check && echo "readiness.check=true" >> /tmp/check
EXPOSE 8080
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install && cp /home/app/target/*.jar /home/app/application.jar && rm /home/app/target/*.jar
ENTRYPOINT ["java","-jar","/home/app/application.jar"]
