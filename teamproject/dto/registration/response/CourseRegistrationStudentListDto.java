package com.example.back404.teamproject.dto.registration.response;

import com.example.back404.teamproject.common.enums.CourseRegistrationApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRegistrationStudentListDto {
    private String studentId;
    private String studentNumber;
    private String studentName;
    private String studentGrade;
    private String studentEmail;
    private CourseRegistrationApprovalStatus courseRegistrationApprovalStatus;
}