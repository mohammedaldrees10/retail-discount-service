package com.example.retaildiscount.dto.request;

import com.example.retaildiscount.model.UserType;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@RecordBuilder
@Builder
@Schema(description = "Request DTO for creating or updating a user")
public record UserRequest(

        @Schema(description = "User name", example = "John Doe")
        @NotBlank
        String name,

        @Schema(description = "User type (EMPLOYEE, CUSTOMER, AFFILIATE)", example = "CUSTOMER")
        @NotNull
        UserType userType,

        @Schema(description = "Date the user was registered", example = "2022-01-01")
        @NotNull
        LocalDate registeredDate
) {
}
