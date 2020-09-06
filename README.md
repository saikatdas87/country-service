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
* [Angular: 8.x.x](https://angular.io/)
* [Angular CLI: 8.x.x](https://cli.angular.io/)
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Maven](https://maven.apache.org/)


### Prerequisites

* [Node.js](https://nodejs.org/) (version 12 or higher)
* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (version 1.8 or higher)
* [NPM](https://www.npmjs.com/get-npm) (Version 6.13.*)

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

* When running the app frontend , the application can be accessed below url

```
    http://localhost:4200/  (Frontend application)
```

To run and access the backend application with only please follow below commands 

```
    mvn spring-boot:run
```

Can be ran directly from IntelliJ IDEA by going to `src/main/java/com/saikat/api/countryservice/CountryServiceApplication.java` and clicking run.

Build Spring Boot Project with Maven

   ```maven package```

Or
    
    mvn install / mvn clean install

* To run tests

``` mvn test```

## Running UI

* Go to `ui` directory and install dependencies by `npm install`

#### Development server

Run `ng serve` from the ui folder for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

#### Build

Run `ng build` to build the project from `ui` directory. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

#### Running unit tests

Run `ng test` from `ui` folder to execute the unit tests via [Karma](https://karma-runner.github.io).

#### Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
