package com.example.back404.teamproject.dto.student.response;

import com.example.back404.teamproject.common.enums.StudentStatus;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import lombok.*;

import java.time.LocalDate;
import java.time.Year;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class StudentGetResponseDto {
    private String studentId;
    private String studentNumber;
    private String studentName;
    private String studentGrade;
    private String studentEmail;
    private String studentPhoneNumber;
    private LocalDate studentBirthDate;
    private SubjectAffiliation studentAffiliation;
    private Year studentAdmissionYear;
    private StudentStatus studentStatus;
}