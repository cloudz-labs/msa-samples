FROM openjdk:8-jdk-alpine
ADD target/labs-apigateway-service-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 9999
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]