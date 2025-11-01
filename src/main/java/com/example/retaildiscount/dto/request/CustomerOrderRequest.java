package com.example.retaildiscount.dto.request;

import com.example.retaildiscount.model.Item;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@RecordBuilder
@Builder
@Schema(description = "Customer order request containing items and user information")
public record CustomerOrderRequest(

        @Schema(description = "User ID who placed the order", example = "a4f882c0-6fd7-4b34-bd6a-2f6e0ac8c888")
        @NotNull UUID userId,
        @Schema(description = "List of items in the order")
        @NotEmpty List<Item> items) {
}
