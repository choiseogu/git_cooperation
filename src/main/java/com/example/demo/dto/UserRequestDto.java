package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(title = "MEM_REQ_01 : 회원가입 요청 DTO")
public class UserRequestDto {

    @NotBlank(message = "id를 입력해주세요.")
    @Size(min = 3, max = 15, message = "id는 3글자 이상 15글자 이하입니다.")
    @Schema(description = "id", example = "john doe")
    private String id;

    @NotBlank
    @Schema(description = "비밀번호", example = "1234!")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 이상 20자 이하여야 합니다.")
    private String pw;
}
