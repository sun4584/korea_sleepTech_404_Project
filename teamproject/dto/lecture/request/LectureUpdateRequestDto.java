package com.example.back404.teamproject.dto.lecture.request;

import com.example.back404.teamproject.common.enums.LectureDayOfWeek;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LectureUpdateRequestDto {
    @NotBlank(message = "교사 ID는 필수입니다.")
    private String teacherId;

    @NotNull(message = "요일은 필수입니다.")
    private LectureDayOfWeek dayOfWeek;

    @NotNull(message = "교시는 필수입니다.")
    @Min(value = 1, message = "교시는 1 이상이어야 합니다.")
    @Max(value = 8, message = "교시는 8 이하여야 합니다.")
    private Integer period;

    @NotNull(message = "최대 인원은 필수입니다.")
    @Min(value = 0, message = "최대 인원은 0 이상이어야 합니다.")
    @Max(value = 30, message = "최대 인원은 30 이하여야 합니다.")
    private Integer maxEnrollment;
}