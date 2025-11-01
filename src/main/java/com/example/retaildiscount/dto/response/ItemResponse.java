package com.example.retaildiscount.dto.response;

import com.example.retaildiscount.model.ItemType;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@RecordBuilder
@Builder
@Schema(description = "Response DTO containing item details")
public record ItemResponse(

        @Schema(description = "Item ID", example = "d1f882c0-6fd7-4b34-bd6a-2f6e0ac8c999")
        UUID id,

        @Schema(description = "Item name", example = "Laptop")
        String name,

        @Schema(description = "Item price", example = "499.99")
        Double price,

        @Schema(description = "Type of item", example = "GROCERY")
        ItemType itemType
) {
}
