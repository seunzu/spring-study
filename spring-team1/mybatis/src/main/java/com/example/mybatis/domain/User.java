package com.example.mybatis.domain;


import com.example.mybatis.request.UserRequest;
import org.springframework.util.StringUtils;

public class User {

    private Long id;
    private String name;
    private String email;

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
    }
    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public void updateUser(UserRequest userRequest) {
        if(StringUtils.hasText(userRequest.email())) {
            this.email = userRequest.email();
        }
        if(StringUtils.hasText(userRequest.name())) {
            this.name = userRequest.name();
        }
    }

    public static class Builder{
        private Long id;
        private String name;
        private String email;

        public Builder id(Long id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public User build() {
            return new User(this);
        }
    }
}
