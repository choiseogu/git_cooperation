package com.example.demo.controller;


import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "user", description = "회원 api")
@RestController //REST API 컨트롤러
@RequestMapping("/api/v1")
public class UserApiController {
    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@RequestBody @Valid RequestDto requestDto) { //@valid : 유효성 검사 수행 어노테이션 -> 유효하지 않는 필드 있다면 400에러 처리
        UserEntity userEntity = userService.createUser(requestDto);
        return ResponseEntity.ok(new ResponseDto(userEntity)); //dto로 변환해 응답
    }

    @GetMapping
    public List<ResponseDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(ResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable String id) {
        return userService.getUserById(id)
                .map(ResponseDto::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable String id, @RequestBody @Valid RequestDto requestDto) {
        try{
            UserEntity userEntity = userService.updateUser(id, requestDto);
            return ResponseEntity.ok(new ResponseDto(userEntity));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}