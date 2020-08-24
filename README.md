# :leaves: ioc-container-intro

Minimalist Java program using only `spring-context` and `spring-webmvc`  for study purposes,
to better how Spring Boot configures WebApplicationContexts, DispatcherServlet, Controllers, global
Exception handling, etc.

The goal is to keep this as simple as possible and cover:

- [x] Refactor [plain java minimal RESTful service](https://github.com/dplucenio/plain-java-minimal-rest-service)
created previosly to use Spring's IoC Container. 
- [x] Setting up WebApplicationContext
- [x] DispatcherServlet
- [x] Controllers
- [x] GET endpoint, @ResponseBody
- [x] POST endpoints, @RequestBody
- [x] @PathVariable e @RequestParam
- [ ] Global exception handling

Progress is going to be stored on different branches, starting from `1-refactor-to-spring-ioc-container`.

This projects uses `maven` and Java 11.

To create the executable jar, run:

```shell script
mvn clean package
```

This will produce a bundle jar. To run the application:

```shell script
java -jar minimal-rest-service-with-spring-webmvc-1.0-SNAPSHOT.jar
```
