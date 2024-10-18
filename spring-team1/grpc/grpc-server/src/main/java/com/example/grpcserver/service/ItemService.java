package com.example.grpcserver.service;

import com.example.grpcserver.domain.Item;
import com.example.grpcserver.mapper.ItemMapper;
import com.example.pb.unit.common.ReturnMsg;
import com.example.pb.unit.item.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ItemService extends ItemServiceGrpc.ItemServiceImplBase {

    private final ItemMapper itemMapper;

    @Override
    public void createItem(CreateItemReq request, StreamObserver<CreateItemRes> responseObserver) {
        Item newItem = Item.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        itemMapper.createItem(newItem);

        CreateItemRes response = CreateItemRes.newBuilder()
                .setId(String.valueOf(newItem.getId()))
                .setResult(buildReturnMsg(newItem.getName(), 200))
                .build();

        sendResponse(responseObserver, response);
    }

    @Override
    public void readItem(ReadItemReq request, StreamObserver<ReadItemRes> responseObserver) {
        Long itemId = Long.parseLong(request.getId());
        Item item = itemMapper.findItemById(itemId);

        ReadItemRes response;

        if (item != null) {
            response = ReadItemRes.newBuilder()
                    .setId(String.valueOf(item.getId()))
                    .setName(item.getName())
                    .setDescription(item.getDescription())
                    .setResult(buildReturnMsg("SUCCESS", 200))
                    .build();
        } else {
            response = ReadItemRes.newBuilder()
                    .setResult(buildReturnMsg("NOT FOUND", 404))
                    .build();
        }

        sendResponse(responseObserver, response);
    }

    @Override
    public void updateItem(UpdateItemReq request, StreamObserver<UpdateItemRes> responseObserver) {
        Item itemToUpdate = Item.builder()
                .id(Long.parseLong(request.getId()))
                .name(request.getName())
                .description(request.getDescription())
                .build();

        itemMapper.updateItem(itemToUpdate);

        UpdateItemRes response = UpdateItemRes.newBuilder()
                .setResult(buildReturnMsg("SUCCESS", 200))
                .build();

        sendResponse(responseObserver, response);
    }

    @Override
    public void deleteItem(DeleteItemReq request, StreamObserver<DeleteItemRes> responseObserver) {
        Long itemId = Long.parseLong(request.getId());
        itemMapper.deleteItem(itemId);

        DeleteItemRes response = DeleteItemRes.newBuilder()
                .setResult(buildReturnMsg("SUCCESS", 200))
                .build();

        sendResponse(responseObserver, response);
    }

    private ReturnMsg buildReturnMsg(String message, int code) {
        return ReturnMsg.newBuilder()
                .setMessage(message)
                .setCode(code)
                .build();
    }

    private <T> void sendResponse(StreamObserver<T> responseObserver, T response) {
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}