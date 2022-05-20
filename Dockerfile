# Start with base image
FROM openjdk:11

# Add Maintainer Info
LABEL maintainer="Progressive Coder"

# Add a temporary volume
VOLUME /tmp

# Expose Port 8081
EXPOSE 8081

# Application Jar File
ARG JAR_FILE=target/MavenAPIRestfull-0.0.1-SNAPSHOT.jar

# Add Application Jar File to the Container
ADD ${JAR_FILE} spring-boot-starter.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/spring-boot-starter.jar"]