FROM maven:3.8.5-openjdk-17 AS build-stage
WORKDIR /application
COPY ./pom.xml ./
COPY ./src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer=" <mohamed.@github.com>"
LABEL version="2.0.0"
LABEL description="API Routing Gateway"
WORKDIR /application
COPY --from=build-stage /application/target/*.jar routing-gateway.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "routing-gateway.jar"]
