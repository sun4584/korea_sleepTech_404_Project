package com.example.back404.teamproject.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResetPasswordRequestDto {
    @NotBlank(message = "사용자명은 필수입니다.")
    private String username;

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "학번은 필수입니다.")
    private String studentNumber;
}