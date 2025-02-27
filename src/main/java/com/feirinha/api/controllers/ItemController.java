package com.feirinha.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feirinha.api.services.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
  
  final ItemService itemService;

  ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

}
