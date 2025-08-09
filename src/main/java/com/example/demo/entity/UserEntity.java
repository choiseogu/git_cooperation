package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Column
    @Id
    private String id;
    private String user_code;
    private String pw;
    private String nickname;
    private String address;
    private String created_date;
}