# Base image
FROM maven:3.5.3-jdk-8-alpine

# Author
LABEL "Author"="Arcifius <augustopaladin@gmail.com>"

# Create robot folders
RUN mkdir -p /opt/robot

# Change workdir
WORKDIR /opt/robot

# Copy pom.xml, scripts and .env
COPY pom.xml .

# Download dependencies
RUN mvn -B -e dependency:resolve

# Send files
COPY . .

# Package
RUN mvn -B -e package

ENTRYPOINT ["java", "-jar", "target/facebook-robot-1.0-SNAPSHOT.jar"]
