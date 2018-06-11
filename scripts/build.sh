#!/bin/bash

# FIND SCRIPT DIR SINCE IT COULD BE EXECUTED FROM ANYWHERE
SCRIPT=$(readlink -f "$0")
SCRIPTPATH=$(dirname "$SCRIPT")

cd $SCRIPTPATH;
cd ..

mvn clean test compile assembly:single&&java -cp target/facebook-robot-1.0-SNAPSHOT-jar-with-dependencies.jar br.com.arcifius.robot.bootstrap.Main