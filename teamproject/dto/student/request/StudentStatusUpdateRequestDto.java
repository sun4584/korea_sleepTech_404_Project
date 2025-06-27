package com.example.back404.teamproject.dto.student.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentStatusUpdateRequestDto {
    @NotNull(message = "학생 ID는 필수입니다.")
    private Long studentId;

    @NotBlank(message = "상태는 필수입니다.")
    private String status;
}