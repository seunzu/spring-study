package com.example.mybatis.controller;

import com.example.mybatis.request.UserRequest;
import com.example.mybatis.response.UserResponse;
import com.example.mybatis.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/find-all")
    public List<UserResponse> findAllUsers() {
        return userService.findAllUsers();
    }
    @PostMapping("/sign-up")
    public void signUpAccount(@RequestBody UserRequest userRequest) {
        userService.signUpAccount(userRequest);
    }
    @PutMapping("/update/{id}")
    public void updateUserInfo(@PathVariable("id") String id, @RequestBody UserRequest userRequest) {
        Long userId = Long.parseLong(id);
        userService.updateUserInfo(userId, userRequest);
    }
    @DeleteMapping("/sign-out/{id}")
    public void accountWithdrawal(@PathVariable Long id){
        userService.accountWithdrawal(id);
    }

    @GetMapping("/search")
    public List<UserResponse> findByNameOrEmail(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email
    ){
        return userService.searchUserByNameOrEmail(name,email);
    }

}
