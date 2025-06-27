package com.example.back404.teamproject.dto.lecture.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureSimpleResponseDto {
    private Long id;
    private String subjectName;
    private String teacherName;
    private String dayOfWeek;
    private int period;
    private int maxStudents;
    private int currentStudents;
}