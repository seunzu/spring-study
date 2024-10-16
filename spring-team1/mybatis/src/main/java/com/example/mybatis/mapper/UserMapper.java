package com.example.mybatis.mapper;

import com.example.mybatis.domain.User;
import com.example.mybatis.response.UserResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Long id);

    @Select("SELECT * FROM users")
    List<UserResponse> findAllUsers();

    @Insert("INSERT INTO users(name, email) VALUES ( #{name}, #{email})")
    void signUpAccount(User user);

    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    void updateUserInfo(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void accountWithdrawal(Long id);
}
