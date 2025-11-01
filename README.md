# Retail Discount Service

## Overview
This Spring Boot REST API calculates the net payable amount for a retail store bill, applying various discounts based on user type and bill amount.

## Features
- Percentage-based and flat discounts
- MongoDB persistence
- Unit tests with Mockito and Integration Test
- Docker & docker-compose support

## Run Locally
```bash
mvn clean install
mvn spring-boot:run
```

## Run with Docker
```bash
docker-compose up --build
```

## Test 
```bash
mvn test
```