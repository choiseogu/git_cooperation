package com.example.demo.service;

import com.example.demo.dto.UserCreateRequestDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> findAllUser() {
        return userRepository.findAll();
    }

    public UserEntity findOne(String id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public void saveUser(UserEntity user) {

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UserEntity user) {
        userRepository.save(user);
    }

    @Transactional(isolation = Isolation.DEFAULT)
    public UserEntity update_addr(String user_id, String address){
        UserEntity chg_addr = userRepository.findOne(user_id);
        chg_addr.setAddress(address);
        return chg_addr;
    }

    @Transactional
    public void deleteUser(String id) {
        userRepository.delete(id);
    }

    public void saveMember(UserCreateRequestDto userCreateRequestDto) {
        UserEntity userEntity = userCreateRequestDto.toEntity();
        userRepository.save(userEntity);
    }
}
