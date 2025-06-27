package com.example.back404.teamproject.dto.student.response;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentListGetResponseDto {
    private String studentId;
    private String studentNumber;
    private String studentName;
    private String studentGrade;
    private String studentEmail;
    private SubjectAffiliation studentAffiliation;
}