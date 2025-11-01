package com.example.retaildiscount.mapper;

import com.example.retaildiscount.dto.request.CustomerOrderRequest;
import com.example.retaildiscount.model.CustomerOrder;
import com.example.retaildiscount.model.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-01T19:40:16+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CustomerOrderMapperImpl implements CustomerOrderMapper {

    @Override
    public CustomerOrder map(CustomerOrderRequest customerOrderRequest) {
        if ( customerOrderRequest == null ) {
            return null;
        }

        CustomerOrder customerOrder = new CustomerOrder();

        customerOrder.setUserId( customerOrderRequest.userId() );
        List<Item> list = customerOrderRequest.items();
        if ( list != null ) {
            customerOrder.setItems( new ArrayList<Item>( list ) );
        }

        return customerOrder;
    }
}
