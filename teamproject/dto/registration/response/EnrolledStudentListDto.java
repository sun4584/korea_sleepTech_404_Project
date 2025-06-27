package com.example.back404.teamproject.dto.registration.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrolledStudentListDto {
    private String studentId;
    private String studentNumber;
    private String studentName;
    private String studentGrade;
    private LocalDate studentBirthDate;
}
