# Base image
FROM maven:3.5.3-jdk-8-slim

# Author
LABEL "Author"="Arcifius <augustopaladin@gmail.com>"

# Create robot folder
RUN mkdir -p /opt/robot
RUN mkdir -p /opt/robot/scripts

# Copy pom.xml and scripts
COPY pom.xml /opt/robot
COPY scripts/build.sh /opt/robot/scripts
COPY scripts/run.sh /opt/robot/scripts
COPY .env /opt/robot

# Change workdir
WORKDIR /opt/robot

# Build
RUN sh scripts/build.sh

# Send files
COPY . /opt/robot

ENTRYPOINT ["sh", "scripts/run.sh"]