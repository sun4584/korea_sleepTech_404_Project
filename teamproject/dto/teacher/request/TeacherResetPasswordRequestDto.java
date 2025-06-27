package com.example.back404.teamproject.dto.teacher.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResetPasswordRequestDto {
    @NotBlank(message = "사용자명은 필수입니다.")
    private String username;

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;
}