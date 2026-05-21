# Build stage
FROM maven:3.9.5-eclipse-temurin-17 AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jdk-alpine
# Be explicit with the JAR file name to avoid ambiguity
COPY --from=build /app/target/workingrecord-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]