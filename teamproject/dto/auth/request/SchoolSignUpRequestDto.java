package com.example.back404.teamproject.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolSignUpRequestDto {
    @NotNull(message = "학교 코드는 필수입니다.")
    private Integer schoolCode;

    @NotBlank(message = "사용자명은 필수입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "생년월일은 필수입니다.")
    private String birthDate;

    @NotBlank(message = "전화번호는 필수입니다.")
    private String phoneNumber;
}