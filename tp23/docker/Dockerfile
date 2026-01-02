FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8088

HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:8088/actuator/health || exit 1

ENV SPRING_CLOUD_CONSUL_HOST=consul \
    SPRING_CLOUD_CONSUL_PORT=8500

ENTRYPOINT ["java", "-jar", "app.jar"]
