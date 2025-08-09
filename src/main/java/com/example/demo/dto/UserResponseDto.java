package com.example.demo.dto;

import com.example.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private String id;
    private String user_code;
    private String pw;
    private String nickname;
    private String address;

    public UserResponseDto(UserEntity user) {
        this.id = user.getId();
        this.user_code = user.getUser_code();
        this.pw = user.getPw();
        this.nickname = user.getNickname();
        this.address = user.getAddress();
    }
}
