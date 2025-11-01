package com.example.retaildiscount.dto.request;

import com.example.retaildiscount.model.ItemType;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@RecordBuilder
@Builder
@Schema(description = "Request DTO for creating or updating an item")
public record ItemRequest(

        @Schema(description = "Item name", example = "Laptop")
        @NotBlank
        String name,

        @Schema(description = "Item price", example = "499.99")
        @NotNull
        Double price,

        @Schema(description = "Type of item", example = "GROCERY")
        @NotNull
        ItemType itemType
) {
}
