FROM openjdk:8-jdk-alpine
ADD target/dtlabs-discovery-service-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]