package com.example.grpcclient.service;

import com.example.grpcclient.dto.ItemResponse;
import com.example.pb.unit.item.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    @GrpcClient("item")
    private ItemServiceGrpc.ItemServiceBlockingStub itemStub;

    public ItemResponse createItem(String name, String description) {
        CreateItemReq request = CreateItemReq.newBuilder()
                .setName(name)
                .setDescription(description)
                .build();

        CreateItemRes response = itemStub.createItem(request);
        return new ItemResponse(
                response.getId(),
                name,
                description,
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

    public ItemResponse updateItem(String itemId, String name, String description) {
        UpdateItemReq request = UpdateItemReq.newBuilder()
                .setId(itemId)
                .setName(name)
                .setDescription(description)
                .build();

        UpdateItemRes response = itemStub.updateItem(request);
        return new ItemResponse(
                itemId,
                name,
                description,
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
