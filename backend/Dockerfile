# Use an official OpenJDK 8 runtime as the base image
FROM openjdk:8-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file of your Spring Boot application into the container
COPY target/backend-0.0.1-SNAPSHOT.jar backend-0.0.1-SNAPSHOT.jar

# Command to run the Spring Boot application
CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]