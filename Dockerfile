# Use a base image with OpenJDK 17
FROM openjdk:17-jdk-slim

# Add label for authors
LABEL authors="vladyslavcherniavskyi"

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file of the application to the container
COPY target/csv-data-nub-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (change if your application uses a different port)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]