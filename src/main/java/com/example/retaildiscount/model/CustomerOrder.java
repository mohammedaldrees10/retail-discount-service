package com.example.retaildiscount.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document(collection = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerOrder {
    @Id
    private UUID id = UUID.randomUUID();
    private UUID userId;
    private List<Item> items;
    private LocalDate orderDate;
}
