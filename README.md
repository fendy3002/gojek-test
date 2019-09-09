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

| Command                           | Use                                            |
|-----------------------------------|------------------------------------------------|
| `exit`                            | Exit the application                           |
| `create_parking_lot <slot>`       | First command to run. It set the parking slots available. Replace `<slot>` with a number of slots desired. For example `create_parking_lot 6`. |
| `park <registration_no> <colour>` | Park a car in available slots. Replace `<registration_no>` with car's registration number, and `<colour>` with car's colour. For example `park KA-01-HH-1234 White`. If no slot available, application will reply with `Sorry, parking lot is full` message. |
| `leave <slot>`                    | To remove a car from it's designated slot. Replace `<slot` with car's parking slot number. Example `leave 4` |
| `status`                          | To display currently parked cars in lot.       |
| `<display>_for_<search>`          | To search for parked cars based on `<search>` parameter, and display based on `<display>` parameter. Available search parameter are: `cars_with_colour` and `registration_number`. Available display are: `slot_numbers` and `registration_numbers`. Example: `slot_numbers_for_cars_with_colour White` |
|                                   |                                                |