package com.example.demo.service;

import com.example.demo.dto.RequestDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
//public clas ~~ implements ~~ : UserService 내 메서드를 이곳에 구현하겠다.
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //create
    @Override
    public UserEntity createUser(RequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id는 필수 입력입니다.");
        }

        UserEntity userEntity = new UserEntity();

        String code = UUID.randomUUID().toString();
        String date = LocalDateTime.now().toString();

        userEntity.setId(requestDto.getId());
        userEntity.setUser_code(code);
        userEntity.setNickname(requestDto.getNickname());
        userEntity.setPw(requestDto.getPw());
        userEntity.setAddress(requestDto.getAddress());
        userEntity.setCreated_date(date);

        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(String id, RequestDto requestDto) {
        if (requestDto.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id 입력은 필수입니다.");
        }

        return userRepository.findById(id)
                .map(userEntity -> {
                    userEntity.setId(requestDto.getId());
                    userEntity.setNickname(requestDto.getNickname());
                    userEntity.setPw(requestDto.getPw());
                    userEntity.setAddress(requestDto.getAddress());
                    return userRepository.save(userEntity);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID가 없습니다."));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();

        if (userEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "등록된 사용자가 없습니다.");
        }

        return userEntities;
    }

    @Override
    public Optional<UserEntity> getUserById(String id) {
        return Optional.ofNullable(userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 id는 등록되어 있지 않습니다.")));
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 사용자가 존재하지 않습니다.");
        }

        userRepository.deleteById(id);
    }
}
