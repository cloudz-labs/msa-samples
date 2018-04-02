FROM openjdk:8-jdk-alpine
ADD target/labs-user-bff-service-1.4.0-SNAPSHOT.jar app.jar
EXPOSE 8031
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]