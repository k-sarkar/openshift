FROM registry.redhat.io/ubi8/ubi-minimal:8.4-208
USER root
RUN microdnf install java-1.8.0-openjdk-devel -y \
    && microdnf install maven -y \
    && microdnf install tar -y 
EXPOSE 8080
COPY src /home/app/src
COPY pom.xml /home/app
COPY buildscript.sh /home/
RUN chmod 777 /home/buildscript.sh
RUN mkdir /properties && touch /properties/additional.properties
RUN mvn -f /home/app/pom.xml clean install && cp /home/app/target/*.jar /home/app/application.jar && rm /home/app/target/*.jar
RUN echo "image version 2"
ENTRYPOINT ["java","-jar","/home/app/application.jar"]
