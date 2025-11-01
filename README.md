# Retail Discount Service

## Overview
This Spring Boot REST API calculates the net payable amount for a retail store bill, applying various discounts based on user type and bill amount.

## Features
- Percentage-based and flat discounts
- MongoDB persistence
- Unit tests with Mockito
- Docker & docker-compose support
- JaCoCo test coverage

## Run Locally
```bash
mvn clean install
mvn spring-boot:run
```

## Run with Docker
```bash
docker-compose up --build
```

## Test & Coverage
```bash
mvn test
mvn jacoco:report
```

Coverage report available at: `target/site/jacoco/index.html`.
