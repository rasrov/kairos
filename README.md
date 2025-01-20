# Kairos
<details>
  <summary><strong>Table of contents</strong></summary>

* [ 🏁 Getting Started ](#-getting-started)
    * [ 🧫 H2 Database ](#-h2-database)
    * [ {...} Swagger ](#-swagger)
    * [ 📞 Cache ](#-cache)
* [ 📦 kairos microservice ](#-kairos-microservice)

</details>

## 🏁 Getting Started

### 🧫 H2 Database

This project are managing data into H2 to simplify the project and because it was a requirement in technical specifications.

By default, H2 are reading sql script from `src/main/resources/data.sql` located into `boot` module. By the way, we can configure this path in other path if we want.

In this script we can find sql statements to create the entities and sql statements to populate these entities.

### {...} Swagger

This microservice is designed from domain, but we have a swagger layer to make use of the `Api First` methodology.

To run it, we just need to access:

http://localhost:5001/swagger-ui/index.html

Additionally, we have the `.apigateway` folder where we can find a yaml file with all the APIs documented.

### 📞 Cache

This project is implementing cache in internal memory to make more fast recurrent calls to the API.

This is implemented with `caffeine` and configured into `boot` module to make sure we are using a single instance at the application level.

## 📦 kairos microservice

This microservice is designed to obtain the price of a product based on certain parameters that we can use as filters or requirements.

The project is ready to run without configuring anything extra.

Just keep in mind to run this project we are using java 21 and spring boot version 3 and higher.

It was built using hexagonal architecture using four modules.

* `application` here we can find our services and the principal business logic.
* `boot` here we can find our application configuration and this is where we have our main file to start application.
* `domain` this module is used to host our project entities.
* `infrastructure` here we can find the jpa spring module that is used to connect to our database and manage data through queries.