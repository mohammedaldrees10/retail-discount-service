package com.example.retaildiscount.dto.response;

import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@RecordBuilder
@Schema(description = "Response containing bill details and calculated amounts")
public record BillResponse(

        @Schema(description = "Unique bill ID", example = "1f3e8e4b-97cd-4a3f-a7f3-4b6a8b4b71e1")
        UUID id,

        @Schema(description = "Associated order ID", example = "b72e9c43-6b1a-4a2d-9f58-5c2c6e089712")
        UUID orderId,

        @Schema(description = "Total amount before discount", example = "990.00")
        Double totalAmount,

        @Schema(description = "Total discount amount", example = "94.50")
        Double discountAmount,

        @Schema(description = "Net payable amount after all discounts", example = "895.50")
        Double netPayableAmount
) {
}
