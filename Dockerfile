# Base image
FROM maven:3.5.3-jdk-8-alpine

# Author
LABEL "Author"="Arcifius <augustopaladin@gmail.com>"

# Create robot folders
RUN mkdir -p /opt/robot

# Change workdir
WORKDIR /opt/robot

# Copy pom.xml
COPY pom.xml .

# Resolve dependencies
RUN mvn dependency:resolve

# Send files
COPY . .

# Package
RUN mvn package

# Start
ENTRYPOINT ["java", "-jar", "target/facebook-robot-1.0-SNAPSHOT.jar"]
