# country-service 

> An application returns all countries with country code

> Also returns some details of a country

> Example :

GET /countries/

response:

          {
                   "countries": [
                             {
                                       "name": "Finland",
                                       "countryCode": "FI"
                             },
                             ...
                   ]
          }

GET /countries/{name}

response:

          {
                   "name": "Finland",
                   "countryCode": "FI",
                   "capital": "Helsinki",
                   "population": 5491817,
                   "flagFileUrl": "<url to the flag file>"
          }

## Used technologies

* [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Maven](https://maven.apache.org/)

### Prerequisites

* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (version 1.8 or higher)

### Let's get started,

* Clone this repository.
* Install maven dependencies
* External API urls (Here used https://restcountries.eu) added in the below property file.
```
    ├── src/main/resources/
    │     ├── application.properties
```
* When running the app (backend), the application can be accessed with below default url

```
    http://localhost:8080/countries   (Backend API returns list of countries with country code)
    http://localhost:8080/countries/{countryName}   (Backend API returns some of the details of a country)
```

To run and access the application with only please follow below commands 

```
    mvn spring-boot:run
```

Can be ran directly from IntelliJ IDEA by going to `com.saikat.code.stickynote.StickyNoteApplication` and clicking run.

Build Spring Boot Project with Maven

   ```maven package```

Or
    
    mvn install / mvn clean install

* To run tests

``` mvn test```
