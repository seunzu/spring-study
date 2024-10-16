package com.example.mybatis.service;

import com.example.mybatis.domain.User;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.request.UserRequest;
import com.example.mybatis.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    public UserResponse findById(Long id) {
        return UserResponse.from(userMapper.findById(id));
    }
    public List<UserResponse> findAllUsers() {
        return userMapper.findAllUsers();
    }
    public void signUpAccount(UserRequest userRequest) {
        User user = userRequest.toEntity(userRequest);
        userMapper.signUpAccount(user);
    }
    @Transactional
    public void updateUserInfo(Long id, UserRequest userRequest){
        User targetUser = userMapper.findById(id);
        if(targetUser != null){
            targetUser.updateUser(userRequest);
            userMapper.updateUserInfo(targetUser);
        }
    }
    public String accountWithdrawal(Long id){
        userMapper.accountWithdrawal(id);
        return "Ok";
    }
}
