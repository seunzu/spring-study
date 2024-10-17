package com.example.mybatis.service;

import com.example.mybatis.domain.User;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.request.UserRequest;
import com.example.mybatis.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserResponse> searchUserByNameOrEmail(String name, String email) {
        List<UserResponse> users = userMapper.findByNameOrEmail(name, email);
        return users.stream()
                .map(user -> new UserResponse(user.id(), user.name(), user.email()))
                .collect(Collectors.toList());
    }
    //이렇게 짜면 그냥 setter로 짠거랑 같나요? 안하는게 좋나요?
}
