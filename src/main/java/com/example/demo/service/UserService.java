package com.example.demo.service;

import com.example.demo.dto.RequestDto;
import com.example.demo.entity.UserEntity;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserEntity createUser(RequestDto requestDto);

    UserEntity updateUser(String id, RequestDto requestDto);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUserById(String id);

    void deleteUser(String id);
}
