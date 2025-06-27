package com.example.back404.teamproject.dto.teacher.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherUpdateRequestDto {
    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "전화번호는 필수입니다.")
    private String phoneNumber;

    private String subject;
}