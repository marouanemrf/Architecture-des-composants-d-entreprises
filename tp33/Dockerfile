FROM eclipse-temurin:17-jdk-alpine

LABEL maintainer=" <mohamed.@github.com>"
LABEL description="Application Cloud Microservice Platform - Spring Boot sur Kubernetes"
LABEL version="2.0.0"
LABEL author=""

WORKDIR /application

COPY target/cloud-microservice-platform-2.0.0-RELEASE.jar application.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","application.jar"]