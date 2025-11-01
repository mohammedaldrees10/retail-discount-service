package com.example.retaildiscount.mapper;

import com.example.retaildiscount.dto.request.ItemRequest;
import com.example.retaildiscount.dto.response.ItemResponse;
import com.example.retaildiscount.model.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "SPRING")
public interface ItemMapper {

    Item map(ItemRequest ItemRequest);

    ItemResponse map(Item Item);

    List<ItemResponse> map(List<Item> Item);
}
