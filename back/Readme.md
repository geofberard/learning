# API Server

## Set Up
To install all dependencies, run :
```
$ cd back
$ mvn clean install
```

## Start local environment
The server can be start/debug directly from the IDE running the `main` method in the `TournamentApplication` class.
However, it is possible to run it in terminal with the command :
### `mvnw spring-boot:run`



## Before commit
To ensure the code quality, run prettier before commiting
```
$ npm run lint:fix
$ npm run prettier
```

## Deployment
The WebApp is hosted on github `gh-pages`, all the code merge in `main`branch is automatically deployed.

http://localhost:8080/api/swagger-ui/index.html
