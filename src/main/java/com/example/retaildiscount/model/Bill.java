package com.example.retaildiscount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "bills")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Bill {
    @Id
    private UUID id = UUID.randomUUID();
    private UUID orderId;
    private double totalAmount;
    private double discountApplied;
    private double netPayableAmount;
}

