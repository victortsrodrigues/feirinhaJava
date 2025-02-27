package com.feirinha.api.services;

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

}
