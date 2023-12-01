# Transactions API

## Overview

This Java-based Spring Boot web application provides functionality for two main tasks:

1. **Store a Purchase Transaction:** Users can store purchase transactions, including details such as description, date, and amount.

2. **Retrieve a Purchase Transaction in a Specified Country’s Currency:** The application fetches exchange rates from the [Fiscal Data Treasury API](https://api.fiscaldata.treasury.gov/services/api/fiscal_service) to convert purchase transactions into a specified country's currency.

You can find a Postman Collection with examples on how to use the API in the root of the project on the file postman_collection.json.

## Technology Stack

- **Java Version:** 21 (GraalVM JDK)
- **Spring Boot:** Framework for building Java-based web applications.
- **Database:** PostgreSQL is used as the underlying database.
- **Monitoring:** Prometheus and Grafana are integrated for monitoring.
- **Testing Library:** JUnit 5 is used for testing.

## JDK Requirements

It is recommended to use GraalVM JDK for this project. While other JDK distributions can be used, GraalVM is necessary if you intend to build native images, which offer smaller and faster execution compared to container images bundling the JVM.

## Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/JoeyJohnJo/WEX-Gateways-Transactions.git
   cd WEX-Gateways-Transactions
   ```

2. **Configure JDK:**
   Ensure that you have GraalVM JDK installed. You can download it from [GraalVM website](https://www.graalvm.org/).

3. **Database Configuration:**
   - PostgreSQL is the chosen database. Configure the database connection in the application properties.

4. **Monitoring Setup:**
   - Prometheus and Grafana are used for monitoring.
      - Prometheus will be accessible at [http://localhost:9090](http://localhost:9090).
      - Grafana will be accessible at [http://localhost:3000](http://localhost:3000) with the initial username and password set to `admin/admin`. Change the password upon login.
      - Recommended Grafana Dashboard ID: 11378 (prebuilt for monitoring Spring Boot applications).

5. **API Configuration:**
   - The project fetches data from the [Fiscal Data Treasury API](https://api.fiscaldata.treasury.gov/services/api/fiscal_service) to get exchange rates. The currency must be provided in the format accepted by the API (e.g., "Canada-Dollar," "Egypt-Pound," "Euro Zone-Euro"). Refer to the [API documentation](https://fiscaldata.treasury.gov/datasets/treasury-reporting-rates-exchange/treasury-reporting-rates-of-exchange) for all accepted values.

6. **Build and Run:**
   - Build and run the application using Spring Boot.

7. **Accessing the Application:**
   - Once the application is running, access it through the specified endpoints for storing and retrieving purchase transactions.

## Testing

- Testing is performed using JUnit 5. Write your tests using this library.

# Running the Application

## Prerequisites

Ensure the following prerequisites are met before running the application:

- **Maven:** Install Maven on your machine.
- **Java JDK 21:** Ensure Java Development Kit (JDK) version 21 is installed. If native builds are desired, GraalVM JDK is recommended.
- **Docker:** Install Docker on your machine.

## Start the Application

```bash
mvn spring-boot:start
```

By executing this command, Maven will initialize your application and the associated services specified in the `docker.yaml` file at the root of the project. As a result, your application will be available on port 8080, Grafana on port 3000, and Prometheus on port 9090.

## Stop the Application

To stop the application and all running containers, you can use the following command:

```bash
mvn spring-boot:stop
```

This command will gracefully shut down your application and stop all associated containers.

Feel free to adjust the configuration or explore other Maven commands based on your needs.

# Building a Native Executable

To build a native executable for the application, execute the following Maven command:

```bash
mvn native:compile
```

This command will generate an executable file. Please note that to start Docker Compose automatically, the `docker-compose.yaml` file needs to be in the same directory as the executable. When executing the generated executable from a different location, ensure that your Docker dependencies are already running.
