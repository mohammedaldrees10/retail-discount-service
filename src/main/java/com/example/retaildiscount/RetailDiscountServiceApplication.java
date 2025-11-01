package com.example.retaildiscount;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Retail Service API", version = "1.0.0", description = "API documentation for the Retail Discount System"))
public class RetailDiscountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RetailDiscountServiceApplication.class, args);
    }
}
