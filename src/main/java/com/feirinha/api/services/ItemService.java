package com.feirinha.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.feirinha.api.dtos.ItemDTO;
import com.feirinha.api.models.ItemModel;
import com.feirinha.api.repositories.ItemRepository;

@Service
public class ItemService {

  final ItemRepository itemRepository;

  ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public Optional<ItemModel> createItem(ItemDTO body) {
    
    if (itemRepository.existsByName(body.getName())) {
      return Optional.empty();
    }
    
    ItemModel item = new ItemModel(body);
    itemRepository.save(item);
    return Optional.of(item);
  }

  public List<ItemModel> getItems() {
    return itemRepository.findAll();
  }

  public Optional<ItemModel> getItemById(Long id) {
    Optional<ItemModel> item = itemRepository.findById(id);

    if (!item.isPresent()) {
      return Optional.empty();
    }
    return item;
  }

  public Optional<ItemModel> updateItem(Long id, ItemDTO body) {
    
    if (itemRepository.existsByName(body.getName())) {
      return Optional.empty();
    }
    
    Optional<ItemModel> item = itemRepository.findById(id);

    if (!item.isPresent()) {
      return Optional.empty();
    }

    ItemModel updatedItem = new ItemModel(body);
    updatedItem.setId(id);
    itemRepository.save(updatedItem);
    return Optional.of(updatedItem);
  }

  public boolean checkExistence(String name) {
    return itemRepository.existsByName(name);
  }

  public Optional<ItemModel> deleteItem(Long id) {
    
    Optional<ItemModel> item = itemRepository.findById(id);
    if (!item.isPresent()) {
      return Optional.empty();
    }
    
    itemRepository.deleteById(id);
    return Optional.of(item.get());
  }

}
