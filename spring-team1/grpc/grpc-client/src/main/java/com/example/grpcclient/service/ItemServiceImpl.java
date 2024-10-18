package com.example.grpcclient.service;

import com.example.grpcclient.dto.*;
import com.example.pb.unit.item.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @GrpcClient("item")
    private ItemServiceGrpc.ItemServiceBlockingStub itemStub;

    public ItemResponse createItem(ItemRequest itemRequest) {
        CreateItemReq request = CreateItemReq.newBuilder()
                .setName(itemRequest.name())
                .setDescription(itemRequest.description())
                .build();

        CreateItemRes response = itemStub.createItem(request);
        return new ItemResponse(
                response.getId(),
                itemRequest.name(),
                itemRequest.description(),
                new ItemResponse.ReturnMsg(response.getResult().getMessage(), response.getResult().getCode())
        );
    }

    public ItemResponse readItem(String itemId) {
        ReadItemReq request = ReadItemReq.newBuilder()
                .setId(itemId)
                .build();

        ReadItemRes response = itemStub.readItem(request);
        return new ItemResponse(
                itemId,
                response.getName(),
                response.getDescription(),
                new ItemResponse.ReturnMsg(response.getResult().getMessage(), response.getResult().getCode())
        );
    }

    public ItemResponse updateItem(String itemId, ItemRequest itemRequest) {
        UpdateItemReq request = UpdateItemReq.newBuilder()
                .setId(itemId)
                .setName(itemRequest.name())
                .setDescription(itemRequest.description())
                .build();

        UpdateItemRes response = itemStub.updateItem(request);
        return new ItemResponse(
                itemId,
                itemRequest.name(),
                itemRequest.description(),
                new ItemResponse.ReturnMsg(response.getResult().getMessage(), response.getResult().getCode())
        );
    }

    public void deleteItem(String itemId) {
        DeleteItemReq request = DeleteItemReq.newBuilder()
                .setId(itemId)
                .build();

        itemStub.deleteItem(request);
    }
}
