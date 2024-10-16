package com.example.grpcserver.service;

import com.example.grpcserver.domain.Item;
import com.example.grpcserver.mapper.ItemMapper;
import com.example.pb.unit.common.ReturnMsg;
import com.example.pb.unit.item.*;
import com.example.pb.unit.item.ItemServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ItemServiceImpl extends ItemServiceGrpc.ItemServiceImplBase {

    private final ItemMapper itemMapper;

    @Override
    public void createItem(CreateItemReq request, StreamObserver<CreateItemRes> responseObserver) {
        Item newItem = Item.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        itemMapper.createItem(newItem);

        ReturnMsg returnMsg = ReturnMsg.newBuilder()
                .setMessage(newItem.getName())
                .setCode(200)
                .build();

        CreateItemRes response = CreateItemRes.newBuilder()
                .setId(String.valueOf(newItem.getId()))
                .setResult(returnMsg)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void readItem(ReadItemReq request, StreamObserver<ReadItemRes> responseObserver) {
        Long itemId = Long.parseLong(request.getId());
        Item item = itemMapper.findItemById(itemId);

        if (item != null) {
            ReturnMsg returnMsg = ReturnMsg.newBuilder()
                    .setMessage("SUCCESS")
                    .setCode(200)
                    .build();

            ReadItemRes response = ReadItemRes.newBuilder()
                    .setId(String.valueOf(item.getId()))
                    .setName(item.getName())
                    .setDescription(item.getDescription())
                    .setResult(returnMsg)
                    .build();

            responseObserver.onNext(response);
        } else {
            ReturnMsg returnMsg = ReturnMsg.newBuilder()
                    .setMessage("NOT FOUND")
                    .setCode(404)
                    .build();

            ReadItemRes response = ReadItemRes.newBuilder()
                    .setResult(returnMsg)
                    .build();

            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void updateItem(UpdateItemReq request, StreamObserver<UpdateItemRes> responseObserver) {
        Item itemToUpdate = Item.builder()
                .id(Long.parseLong(request.getId()))
                .name(request.getName())
                .description(request.getDescription())
                .build();

        itemMapper.updateItem(itemToUpdate);

        ReturnMsg returnMsg = ReturnMsg.newBuilder()
                .setMessage("SUCCESS")
                .setCode(200)
                .build();

        UpdateItemRes response = UpdateItemRes.newBuilder()
                .setResult(returnMsg)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteItem(DeleteItemReq request, StreamObserver<DeleteItemRes> responseObserver) {
        Long itemId = Long.parseLong(request.getId());
        itemMapper.deleteItem(itemId);

        ReturnMsg returnMsg = ReturnMsg.newBuilder()
                .setMessage("SUCCESS")
                .setCode(200)
                .build();

        DeleteItemRes response = DeleteItemRes.newBuilder()
                .setResult(returnMsg)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}