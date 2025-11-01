package com.example.retaildiscount.dto.response;

import com.example.retaildiscount.model.UserType;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@RecordBuilder
@Builder
@Schema(description = "Response DTO containing user details")
public record UserResponse(

        @Schema(description = "User ID", example = "a4f882c0-6fd7-4b34-bd6a-2f6e0ac8c888")
        UUID id,

        @Schema(description = "User name", example = "John Doe")
        String name,

        @Schema(description = "User type (EMPLOYEE, CUSTOMER, AFFILIATE)", example = "CUSTOMER")
        UserType userType,

        @Schema(description = "Date the user was registered", example = "2022-01-01")
        LocalDate registeredDate
) {
}
