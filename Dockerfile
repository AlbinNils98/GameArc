FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy  jar into the container
COPY target/GameArc-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the app with limited JVM memory
CMD ["java", "-Xms256m", "-Xmx384m", "-XX:MaxMetaspaceSize=128m", "-jar", "app.jar"]