package com.feirinha.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feirinha.api.dtos.ItemDTO;
import com.feirinha.api.models.ItemModel;
import com.feirinha.api.services.ItemService;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/items")
public class ItemController {

  final ItemService itemService;

  ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping()
  public ResponseEntity<Object> createItem(@RequestBody @Valid ItemDTO body) {
    Optional<ItemModel> item = itemService.createItem(body);

    if (!item.isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Item already exists!");
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(item.get());
  }
      
  

}
