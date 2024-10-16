package com.example.grpcclient.controller;

import com.example.grpcclient.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public String createItem(@RequestParam String name, @RequestParam String description) {
        return itemService.createItem(name, description);
    }

    @GetMapping("/{itemId}")
    public String readItem(@PathVariable String itemId) {
        return itemService.readItem(itemId);
    }

    @PutMapping("/{itemId}")
    public String updateItem(@PathVariable String itemId, @RequestParam String name, @RequestParam String description) {
        return itemService.updateItem(itemId, name, description);
    }

    @DeleteMapping("/{itemId}")
    public String deleteItem(@PathVariable String itemId) {
        return itemService.deleteItem(itemId);
    }
}