package com.example.back404.teamproject.dto.registration.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseRegistrationResponseDto {
    private Long registrationId;
    private Long lectureId;
    private String lectureName;
    private String subjectName;
    private String teacherName;
    private String dayOfWeek;
    private int period;
}