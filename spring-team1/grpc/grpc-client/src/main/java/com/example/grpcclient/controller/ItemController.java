package com.example.grpcclient.controller;

import com.example.grpcclient.dto.*;
import com.example.grpcclient.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ItemResponse createItem(@RequestBody ItemRequest request) {
        return itemService.createItem(request);
    }

    @GetMapping("/{itemId}")
    public ItemResponse readItem(@PathVariable String itemId) {
        return itemService.readItem(itemId);
    }

    @PutMapping("/{itemId}")
    public ItemResponse updateItem(@PathVariable String itemId, @RequestBody ItemRequest request) {
        return itemService.updateItem(itemId, request);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable String itemId) {
       itemService.deleteItem(itemId);
    }
}
