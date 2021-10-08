# Shopping basket application

## Prerequisites

Ensure that you have installed:

- [Git](https://git-scm.com/)
- [Java 11](https://www.oracle.com/java/technologies/downloads/#java11) or later
- [Maven](https://maven.apache.org/)

## How to build

In order to build execute next command:

```
mvn clean package
```

## How to run it

In order to run execute next command:

```
java -jar target/shopping-basket-1.0-SNAPSHOT.jar <JOB_NAME> <ARG1> <ARG2> ... <ARGN>
```

#### Args

|Name|Description|Values|
|--- | --- | --- |
|JOB_NAME| Job name | PriceBasket |
|ARG1..ARGN| Products | [soup, bread, milk, apples] (To modify list go to application.conf) |
