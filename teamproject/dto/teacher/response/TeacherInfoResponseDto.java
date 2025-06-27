package com.example.back404.teamproject.dto.teacher.response;

import com.example.back404.teamproject.common.enums.TeacherStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TeacherInfoResponseDto {
    private String teacherId;
    private String teacherName;
    private String teacherEmail;
    private String teacherPhoneNumber;
    private String teacherUsername;
    private String teacherSubject;
    private TeacherStatus teacherStatus;
    private LocalDateTime createdAt;
}