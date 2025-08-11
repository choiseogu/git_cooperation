package com.example.demo.repository;


import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository<UserEntity, String>는 <엔티티 타입, 기본키 타입>임.
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsById(String id);

    Optional<UserEntity> findById(String id);
}
