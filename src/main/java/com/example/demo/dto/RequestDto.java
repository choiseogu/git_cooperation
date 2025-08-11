package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestDto {
    @Schema(description = "사용자 id", example = "s2000ten")
    private String id;

    private String nickname;

    private String pw;

    private String address;
}
