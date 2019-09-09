# Parking Lot

A parking lot management system.

## Setup

Using pre-installed maven:

```shell
$ git clone https://github.com/fendy3002/gojek-test.git
$ cd gojek-test
$ mvn compile && mvn exec:java
```

Using pre-installed docker:

```shell
$ git clone https://github.com/fendy3002/gojek-test.git
$ cd gojek-test
$ ./compile.sh && ./run.sh
```

Give execute permission via `chmod` to `.sh` files if needed beforehand.

To build jar using pre-installed maven:
```shell
$ mvn clean compile assembly:single
```

Using pre-installed docker:
```shell
$ ./build_jar.sh
```

## Using app

This application will take statements as command lines, either by manually inputted each line or by reading a text file. After finishing setup and build jar (either using pre-installed maven or docker), you can start the application using `java -jar ./bin/parking_lot.jar`. Otherwise you can run the application using docker with running `./run_jar.sh`.

Running application without additional parameter will run in input mode. In this mode, user will be asked to input each commands manually. To make the application read a file input, pass a path to file as a parameter. For example: `java -jar ./bin/parking_lot.jar file_inputs.txt`.

### Commands

These are following commands available to use:

| Command                  | Use                          |
|--------------------------|------------------------------|
| `exit`                   | Exit the application         |
| `create_parking_lot $1`  | First command to run. It set the parking slots available. Replace $1 with a number of slots desired. For example `create_parking_lot 6`. |
|                          |                              |
|                          |                              |