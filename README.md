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

