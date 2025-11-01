package com.example.retaildiscount.mapper;

import com.example.retaildiscount.dto.request.CustomerOrderRequest;
import com.example.retaildiscount.model.CustomerOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "SPRING")
public interface CustomerOrderMapper {

    CustomerOrder map(CustomerOrderRequest customerOrderRequest);
}
