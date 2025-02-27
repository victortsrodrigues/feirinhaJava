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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

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

  @GetMapping()
  public ResponseEntity<Object> getItems() {
    return ResponseEntity.status(HttpStatus.OK).body(itemService.getItems());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getItemById(@PathVariable("id") Long id) {
    Optional<ItemModel> item = itemService.getItemById(id);

    if (!item.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(item.get());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateItem(@PathVariable() Long id, @RequestBody ItemDTO body) {
    
    if(itemService.checkExistence(body.getName())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Item already exists!");
    }
    
    Optional<ItemModel> item = itemService.updateItem(id, body);
    if (!item.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found!");
    }
    return ResponseEntity.status(HttpStatus.OK).body(item.get());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteItem(@PathVariable("id") Long id) {
    Optional<ItemModel> item = itemService.deleteItem(id);
    if (!item.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found!");
    }
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
