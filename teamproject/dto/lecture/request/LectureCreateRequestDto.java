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
public class LectureCreateRequestDto {
    
    @NotBlank(message = "과목 ID는 필수입니다.")
    private String subjectId;

    @NotNull(message = "요일은 필수입니다.")
    private LectureDayOfWeek dayOfWeek;

    @NotNull(message = "교시는 필수입니다.")
    @Min(value = 1, message = "교시는 1 이상이어야 합니다.")
    @Max(value = 8, message = "교시는 8 이하여야 합니다.")
    private Integer period;

    @NotBlank(message = "허용 학년은 필수입니다.")
    private String allowedGrade;

    @NotNull(message = "최대 인원은 필수입니다.")
    @Min(value = 1, message = "최대 인원은 1 이상이어야 합니다.")
    @Max(value = 30, message = "최대 인원은 30 이하여야 합니다.")
    private Integer maxEnrollment;

    private Long classroomId; // 강의실은 선택사항
}