package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Tag(name = "user", description = "회원 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserApiController {

    private final UserService userService;

    @GetMapping("/user")
    @Operation(summary = "회원 전체 조회", description = "url을 통한 회원 전체 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = {@Content(schema = @Schema(implementation = BasicResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "not found"),
    })
    public BasicResponseDto<List<UserResponseDto>> findAllUser() {
        List<UserEntity> all_user = userService.findAllUser();

        if (all_user.isEmpty()){
            return new BasicResponseDto<>(false, "there no user list", null);
        }

        List<UserResponseDto> user_list = all_user.stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());

        return new BasicResponseDto<>(true, "Member list retrived successfully", user_list);

    }

    @GetMapping("/user/{id}")
    @Operation(summary = "회원 정보 조회", description = "id를 통해 회원 정보를 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = {@Content(schema = @Schema(implementation = BasicResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "not found"),
    })
    public BasicResponseDto<UserResponseDto> findoneUser(
            @PathVariable
            @Schema(description = "회원 id", example = "john123")
            String id
    ) {
        UserEntity user = userService.findOne(id);

        if (user == null) {
            return new BasicResponseDto<>(false, "user not found", null);
        }

        UserResponseDto userDto = new UserResponseDto(user);


        return new BasicResponseDto<>(true, "member info find", userDto);
    }

    @PostMapping
    @Operation(summary = "신규 회원 추가", description = "id 겹칠 시 update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = {@Content(schema = @Schema(implementation = BasicResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "not found"),
    })
    public UserEntity createUser(@RequestBody UserCreateRequestDto userCreateRequestDto) {

        String code = UUID.randomUUID().toString();
        String date = LocalDateTime.now().toString();

        UserEntity newUser = new UserEntity();
        newUser.setId(userCreateRequestDto.getId());
        newUser.setUser_code(code);
        newUser.setNickname(userCreateRequestDto.getNickname());
        newUser.setPw(userCreateRequestDto.getPw());
        newUser.setAddress(userCreateRequestDto.getAddress());
        newUser.setCreated_date(date);

        return userService.saveUser(newUser);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public String delete(@RequestBody UserIdDto userIdDto) {
        userService.deleteUser(userIdDto.getId());
        return "delete complete";
    }
}