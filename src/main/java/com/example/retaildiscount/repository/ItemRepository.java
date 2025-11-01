package com.example.retaildiscount.repository;


import com.example.retaildiscount.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRepository extends MongoRepository<Item, UUID> {
}
