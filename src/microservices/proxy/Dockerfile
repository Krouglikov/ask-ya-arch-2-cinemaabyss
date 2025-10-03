FROM maven:3.8.6 AS builder
WORKDIR /app
COPY pom.xml .
COPY src/ ./src/
RUN mvn package

FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=/app/target/*.jar
COPY --from=builder ${JAR_FILE} proxy.jar
ENTRYPOINT ["java", "-jar", "proxy.jar"]