# Cart Management API

API RESTful to register cart(s) with specified item(s) in certain supermarket.

## Documentation

Information how to run, endpoints and some other information can be found in "/presentation/Cart Management API Documentation.pdf" file.

## Project Structure

The project is a Java 11 application composed by Maven, Spring Boot, JPA and Openfeign to compose the whole API. Those libraries help the developer to focus on the main core application without needing to worry about working in connections.

### Maven

This project uses a tool for automation and compilation called [Maven](https://maven.apache.org/). With that tool is possible to manage
dependencies with ease and allow the code to easily be compiled in any environment.

### Spring framework

[Spring](https://docs.spring.io/spring-framework/docs/current/reference/html) framework is responsible for helping
code development so the main focus stays on business logic, offering a wide variety of tools and pre-made code to
the application, increasing the productivity in Java development.

### Spring Web

[Spring Web](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc) is a library responsible for API core of this project, making it easier to manage endpoints, connection and WebServer connection, abstracting the communication layer, dealing with HTTP requests and responses allowing Java Objects to be converted in JSON format.

### Spring Data JPA

[Spring JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/) library configures and creates a connection for Jakarta Persistence API (as known as JPA) that eases access to persistence APIs, in this project it is used to persist queries in MySQL database.

### Spring Boot

[Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) is a library that helps compile the whole application in a stand-alone JAR, making it possible to execute in Java. With previous Spring libraries, it packages the framework, web server and connection to persistence without any other dependency. 

### Other Spring Libraries

[Spring Cache](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-caching.html) is used to provide in memory cache for frequently accessed codes boosting performance inside the application.

[Spring Validation](https://docs.spring.io/spring-framework/docs/3.0.x/reference/validation.html) is a tool to validate business logic automatically, in this project, it is used to validate if fields from request body is not null.

[Spring Test](https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/html/boot-features-testing.html) focus on managing tests with JUnit, AssertJ and other libraries compiled to make sure the project stays working as intended with their respective business logic.

[Openfeign](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/) is a library that helps the developer create connections with other external APIs. In this project it is used to make connections with an external API.

[Lombok](https://projectlombok.org/) is a library that automatically creates most used methods for Java POJO classes.

[Mapstruct](https://mapstruct.org/) is a library that helps the developer to automatically map two POJOs. 

[Swagger OpenAPI 3.0](https://swagger.io/specification/) is a tool that creates documentation for HTTP requests and responses that both humans and machine can understand.

### Development flow

The project follows Git versioning, which consists a main branch as final product, and subsequent built for development purposes. More information [here](https://guides.github.com/introduction/flow/).

### Database

This project uses a pre-made connection to [MySQL](https://dev.mysql.com/doc/) to store data sent by the user through JPA. A library called [MySQL connector](https://dev.mysql.com/doc/connector-j/8.0/en/) was used so when the application starts, it automatically connects with a pre established information provided by the user to an actual running MySQL data.

### Docker

To wrap the whole application in an easy, portable and ready to run in any computer and Cloud applications, it was used [Docker](https://docs.docker.com/) to build a container image of the application, without the need to install an IDE or even an S.O. to run the application.

## What can be done

- Create an actual Supermarket API to make the application realistic and more reliable;
- Insert more details about items;
- Implement an DevOps platform to monitor data.