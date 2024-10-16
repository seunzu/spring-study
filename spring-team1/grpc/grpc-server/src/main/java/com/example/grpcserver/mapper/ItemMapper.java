package com.example.grpcserver.mapper;

import com.example.grpcserver.domain.Item;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ItemMapper {

    @Select("SELECT * FROM items WHERE id = #{id}")
    Item findItemById(Long id);

    @Insert("INSERT INTO items(name, description) VALUES(#{name}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createItem(Item item);

    @Update("UPDATE items SET name = #{name}, description = #{description} WHERE id = #{id}")
    void updateItem(Item item);

    @Delete("DELETE FROM items WHERE id = #{id}")
    void deleteItem(Long id);
}