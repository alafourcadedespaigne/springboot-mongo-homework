# Spring Boot MongoDB Nisum Homework

This application was developed to demonstrate Spring Boot integration with MongoDB for the Nisum company

Technologies Used

- Spring Boot 2.4.2
- Spring Data MongoDB
- Lombok
- Mapstruct
- MongoDB
- Swagger

How to Run this application

First dowload or clone this project from [GitHub](https://github.com/alafourcadedespaigne/springboot-mongo-homework).

```shell
$ git clone https://github.com/alafourcadedespaigne/springboot-mongo-homework.git
```

Then, navigate to application root folder and execute,

```shell
$ ./gradlew clean build
```

There should now be a newly created jar file with all the files needed to run this application in the build / libs folder.

Then build the build with docker compose to build the docker image using the built-in jar file.

```shell
$ docker-compose build
```

Then use following command to run whole setup using docker compose.

```shell
$ docker-compose up
```

### To test the application go to the url

[http://localhost:8080/api/swagger-ui.html](http://localhost:8080/api/swagger-ui.html).
