package com.example.back404.teamproject.dto.teacher.response;

import com.example.back404.teamproject.common.enums.TeacherStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherListGetResponseDto {
    private String teacherId;
    private String teacherUsername;
    private String teacherName;
    private String teacherEmail;
    private String teacherPhoneNumber;
    private String teacherSubject;
    private TeacherStatus teacherStatus;
}