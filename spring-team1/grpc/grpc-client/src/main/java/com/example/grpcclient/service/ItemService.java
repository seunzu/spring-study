package com.example.grpcclient.service;

import com.example.grpcclient.dto.*;

public interface ItemService {

    ItemResponse createItem(ItemRequest itemRequest);
    ItemResponse readItem(String itemId);
    ItemResponse updateItem(String itemId, ItemRequest itemRequest);
    void deleteItem(String itemId);


}
