# Base image
FROM maven:3.5.3-jdk-8-slim

# Author
LABEL "Author"="Arcifius <augustopaladin@gmail.com>"

# Create robot folder
RUN mkdir -p /opt/robot

# Copy pom.xml and scripts
COPY pom.xml /opt/robot
COPY scripts /opt/robot

# Change workdir
WORKDIR /opt/robot

# Resolve dependencies
RUN mvn dependency:resolve

# Send files
COPY . /opt/robot

# Build
RUN sh scripts/build.sh

ENTRYPOINT ["sh", "scripts/run.sh"]