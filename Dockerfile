# Stage 1: Build the jar with Maven
FROM maven:3.9.3-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Copy pom.xml and src folder into the container
COPY pom.xml .
COPY src ./src

# Build the jar file, skipping tests to speed up build
RUN mvn clean package -DskipTests

# Stage 2: Run the Spring Boot app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy jar from the build stage
COPY --from=build /app/target/GameArc-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Run the app with limited JVM memory
CMD ["java", "-Xms256m", "-Xmx384m", "-XX:MaxMetaspaceSize=128m", "-jar", "app.jar"]