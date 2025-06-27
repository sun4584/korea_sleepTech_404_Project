package com.example.back404.teamproject.dto.teacher.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherStatusUpdateRequestDto {

    @NotNull(message = "교사 ID는 필수입니다.")
    private Long teacherId;

    @NotBlank(message = "상태는 필수입니다.")
    private String status;
}