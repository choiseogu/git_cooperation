package com.example.demo.dto;

import com.example.demo.entity.UserEntity;
import lombok.Data;

@Data
public class ResponseDto {
    private String id;
    private String user_code;
    private String nickname;
    private String pw;
    private String address;
    private String created_date;

    //엔티티로부터 데이터를 받아 dto로 변환
    public ResponseDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.user_code = userEntity.getUser_code();
        this.nickname = userEntity.getNickname();
        this.pw = userEntity.getPw();
        this.address = userEntity.getAddress();
        this.created_date = userEntity.getCreated_date();
    }
}
