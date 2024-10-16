package com.example.mybatis.request;

import com.example.mybatis.domain.User;

public record UserRequest(
        String name,
        String email


) {
    public User toEntity(UserRequest userRequest) {
        return new User.Builder()
                .name(userRequest.name)
                .email(userRequest.email)
                .build();
    }
    public User changeInfo(User updateInfo) {
        updateInfo.updateUser(this);
        return updateInfo;
    }
}
