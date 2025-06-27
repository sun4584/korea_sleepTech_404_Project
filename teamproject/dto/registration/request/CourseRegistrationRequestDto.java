package com.example.back404.teamproject.dto.registration.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistrationRequestDto {
    @NotNull(message = "강의 ID는 필수입니다.")
    private Long lectureId;
}