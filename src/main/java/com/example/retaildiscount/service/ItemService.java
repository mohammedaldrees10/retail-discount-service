package com.example.retaildiscount.service;

import com.example.retaildiscount.dto.request.ItemRequest;
import com.example.retaildiscount.dto.response.ItemResponse;
import com.example.retaildiscount.mapper.ItemMapper;
import com.example.retaildiscount.model.Item;
import com.example.retaildiscount.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public List<ItemResponse> getAllItems() {
        return itemMapper.map(itemRepository.findAll());
    }

    public ItemResponse getItemById(UUID id) {
        return itemMapper.map(itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Item not found")));
    }

    public ItemResponse createItem(ItemRequest item) {
        return itemMapper.map(itemRepository.save(itemMapper.map(item)));
    }

    public ItemResponse updateItem(UUID id, ItemRequest updatedItem) {
        Item existing = itemRepository.findById(id).orElseThrow();
        existing.setName(updatedItem.name());
        existing.setPrice(updatedItem.price());
        existing.setItemType(updatedItem.itemType());
        return itemMapper.map(itemRepository.save(existing));
    }

    public void deleteItem(UUID id) {
        itemRepository.deleteById(id);
    }
}
