FROM maven:3.8.6 as build
WORKDIR /app
COPY pom.xml .
COPY src/ ./src/
RUN mvn package

FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} events.jar
ENTRYPOINT ["java", "-jar", "events.jar"]