# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the executable JAR file from the target directory into the container at /app
COPY target/auth-0.0.1-SNAPSHOT.jar /app/auth-service.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "my-spring-boot-app.jar"]
