#!/bin/bash

# FIND SCRIPT DIR SINCE IT COULD BE EXECUTED FROM ANYWHERE
SCRIPT=$(readlink -f "$0")
SCRIPTPATH=$(dirname "$SCRIPT")

cd $SCRIPTPATH;
cd ..

if [ -e target/facebook-robot-1.0-SNAPSHOT-jar-with-dependencies.jar ]
then
    echo "Executing latest build"
    java -jar target/facebook-robot-1.0-SNAPSHOT-jar-with-dependencies.jar
else
    echo "You have to build first!"
fi
