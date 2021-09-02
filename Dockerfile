FROM maven:3.8.2-jdk-8
COPY src /home/app/src
COPY pom.xml /home/app
RUN java -version
RUN mvn -f /home/app/pom.xml clean install && ls -l /home/app/target/
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/target/openshift-0.0.1-SNAPSHOT.jar"]