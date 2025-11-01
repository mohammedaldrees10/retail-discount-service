package com.example.retaildiscount.mapper;

import com.example.retaildiscount.dto.request.ItemRequest;
import com.example.retaildiscount.dto.response.ItemResponse;
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
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item map(ItemRequest ItemRequest) {
        if ( ItemRequest == null ) {
            return null;
        }

        Item item = new Item();

        item.setName( ItemRequest.name() );
        if ( ItemRequest.price() != null ) {
            item.setPrice( ItemRequest.price() );
        }
        item.setItemType( ItemRequest.itemType() );

        return item;
    }

    @Override
    public ItemResponse map(Item Item) {
        if ( Item == null ) {
            return null;
        }

        ItemResponse.ItemResponseBuilder itemResponse = ItemResponse.builder();

        itemResponse.id( Item.getId() );
        itemResponse.name( Item.getName() );
        itemResponse.price( Item.getPrice() );
        itemResponse.itemType( Item.getItemType() );

        return itemResponse.build();
    }

    @Override
    public List<ItemResponse> map(List<Item> Item) {
        if ( Item == null ) {
            return null;
        }

        List<ItemResponse> list = new ArrayList<ItemResponse>( Item.size() );
        for ( Item item : Item ) {
            list.add( map( item ) );
        }

        return list;
    }
}
