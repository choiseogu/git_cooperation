package com.example.demo.dto;

import com.example.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreateRequestDto {
    private String id;
    private String pw;
    private String nickname;
    private String address;

    public static UserCreateRequestDto fromEntity(UserEntity userEntity) {
        return new UserCreateRequestDto(userEntity.getId(), userEntity.getPw(), userEntity.getNickname(), userEntity.getAddress());
    }

    public UserEntity toEntity() {
        return new UserEntity(this.id, null, this.pw, this.nickname, this.address, null);

    }
}
