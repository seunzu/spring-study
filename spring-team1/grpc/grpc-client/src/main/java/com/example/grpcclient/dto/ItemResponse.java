package com.example.grpcclient.dto;

public record ItemResponse(String id, String name, String description, ReturnMsg result) {

    public record ReturnMsg(String message, int code) {}
}
