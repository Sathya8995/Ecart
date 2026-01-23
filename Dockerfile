# build stage
FROM maven:3.9.4-openjdk-23 AS builder
WORKDIR /build
COPY . .
RUN ./mvnw clean package -DskipTests
# runtime stage
FROM openjdk:23-jdk-slim
WORKDIR /app
COPY --from=builder /build/target/ecart-0.0.1-SNAPSHOT.jar /app/ecart.jar
COPY src/main/resources/application-prod.properties /app/config/application-prod.properties
COPY ./.env /app/.env
ENV SPRING_CONFIG_LOCATION=/app/config/application-prod.properties
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java","-jar","/app/ecart.jar"]