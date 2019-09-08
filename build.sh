#!/bin/sh

SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
BASEDIR=$(dirname "$0")

if [ ! "$(docker ps -q -f name=my_java_compiler)" ]; then
    docker run -t -d --name my_java_compiler -v "$SCRIPTPATH":/usr/src/mymaven -w /usr/src/mymaven/my-app maven:3.6.1-jdk-11 bash
fi

docker exec -it my_java_compiler mvn compile