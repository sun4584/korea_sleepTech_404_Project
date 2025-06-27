package com.example.back404.teamproject.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInRequestDto {

    @NotNull(message = "학교 코드는 필수입니다.")
    private Integer schoolCode;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}