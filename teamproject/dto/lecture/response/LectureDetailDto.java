package com.example.back404.teamproject.dto.lecture.response;

import com.example.back404.teamproject.common.enums.LectureDayOfWeek;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureDetailDto {
    private Long lectureId;
    private String subjectName;
    private String teacherName;
    private LectureDayOfWeek dayOfWeek;
    private int period;
    private int grade;
    private int maxEnrollment;
}