FROM openjdk:8-jdk-alpine
ADD target/labs-registration-service-1.2.0-SNAPSHOT.jar app.jar
EXPOSE 8090 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]