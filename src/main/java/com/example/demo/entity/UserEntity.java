package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_prac")
@Getter @Setter
public class UserEntity {
    @Column
    @Id
    private String id;
    private String user_code;
    private String pw;
    private String nickname;
    private String address;
    private String created_date;

//    freshman fix code
//            .
//            .
//            .
//            .
//            .
//    freshman fix end


    public UserEntity() {}

    public UserEntity(String id, String pw, String nickname, String address) {
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.address = address;
    }
}