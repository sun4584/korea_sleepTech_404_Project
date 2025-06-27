package com.example.back404.teamproject.dto.school.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolInfoUpdateRequestDto {
    @NotBlank(message = "전화번호는 필수입니다.")
    private String phoneNumber;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String schoolEmail;

    private String schoolAddress;

    private String schoolContactNumber;
}