# Build stage
FROM maven:3.9.5-eclipse-temurin-17 AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app.jar"]