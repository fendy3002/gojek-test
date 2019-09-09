#!/bin/sh

SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
BASEDIR=$(dirname "$0")

docker run --rm -it -v "$SCRIPTPATH":/usr/src/myapp -w /usr/src/myapp openjdk:11 java -jar ./bin/parking_lot.jar $1