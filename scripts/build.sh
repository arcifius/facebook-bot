#!/bin/bash

# FIND SCRIPT DIR SINCE IT COULD BE EXECUTED FROM ANYWHERE
SCRIPT=$(readlink -f "$0")
SCRIPTPATH=$(dirname "$SCRIPT")

cd $SCRIPTPATH;
cd ..

mvn clean compile assembly:single