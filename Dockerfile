# Base image
FROM maven:3.5.3-jdk-8-slim

# Author
LABEL "Author"="Arcifius <augustopaladin@gmail.com>"

# Create robot folder
RUN mkdir -p /opt/robot

# Copy pom.xml and scripts
COPY pom.xml /opt/robot

# Change workdir
WORKDIR /opt/robot

# Send files
COPY . /opt/robot

# Build & Start robot
RUN mvn clean install

# Start robot
RUN sh scripts/run.sh &