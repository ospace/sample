package com.example.demo;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findById(String id);
    void save(User user);
}
