# ShoppingCart

This app was created to show one example of Order and Product implementation using Spring boot and Spring Security

## Running

When starting the application `docker compose up` is called and the app will connect to the contained services.
[Docker](https://www.docker.com/get-started/) must be available on the current system.

To run the application is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options". Create your own
`application-local.yml` file to override settings for development.

Lombok must be supported by your IDE. For IntelliJ install the Lombok plugin and enable annotation processing

After starting the application it is accessible under `localhost:8080`.

The default user and password are `admin`

There is also a Swagger on the main page with the documentation

## Build

The application can be built using the following command:

```
gradlew clean build
```

Start your application with the following command - here with the profile `local`:

```
java -Dspring.profiles.active=local -jar ./build/libs/shopping-cart-0.0.1-SNAPSHOT.jar
```

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
gradlew bootBuildImage --imageName=digital.wtl/shopping-cart
```

