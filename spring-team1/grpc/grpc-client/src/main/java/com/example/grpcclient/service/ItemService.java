package com.example.grpcclient.service;

import com.example.pb.unit.item.*;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    @GrpcClient("itemService")
    private ItemServiceGrpc.ItemServiceBlockingStub itemStub;

    public String createItem(String name, String description) {
        CreateItemReq request = CreateItemReq.newBuilder()
                .setName(name)
                .setDescription(description)
                .build();

        CreateItemRes response = itemStub.createItem(request);
        return response.getResult().getMessage();
    }

    public String readItem(String itemId) {
        ReadItemReq request = ReadItemReq.newBuilder()
                .setId(itemId)
                .build();

        ReadItemRes response = itemStub.readItem(request);
        return response.getName() + ": " + response.getDescription();
    }

    public String updateItem(String itemId, String name, String description) {
        UpdateItemReq request = UpdateItemReq.newBuilder()
                .setId(itemId)
                .setName(name)
                .setDescription(description)
                .build();

        UpdateItemRes response = itemStub.updateItem(request);
        return response.getResult().getMessage();
    }

    public String deleteItem(String itemId) {
        DeleteItemReq request = DeleteItemReq.newBuilder()
                .setId(itemId)
                .build();

        DeleteItemRes response = itemStub.deleteItem(request);
        return response.getResult().getMessage();
    }
}
