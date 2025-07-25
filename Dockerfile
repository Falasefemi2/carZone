# Build stage
FROM maven:3.9.4-amazoncorretto-21 AS build

# Set the working directory
WORKDIR /app

# Copy pom.xml and download dependencies (for better caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:21.0.4-alpine3.18

# Set the working directory in the container
WORKDIR /app

# Create non-root user for security
RUN adduser -D -s /bin/sh appuser
USER appuser

# Copy the JAR file from build stage
COPY --from=build /app/target/carZone-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]