#!/bin/bash

# FIND SCRIPT DIR SINCE IT COULD BE EXECUTED FROM ANYWHERE
SCRIPT=$(readlink -f "$0")
SCRIPTPATH=$(dirname "$SCRIPT")

cd $SCRIPTPATH;

if [ -e ../target/facebook-robot-1.0-SNAPSHOT-jar-with-dependencies.jar ]
then
    echo "Executing latest build"
    java -cp ../target/facebook-robot-1.0-SNAPSHOT-jar-with-dependencies.jar br.com.arcifius.robot.bootstrap.Main
else
    echo "You have to build first!"
fi
