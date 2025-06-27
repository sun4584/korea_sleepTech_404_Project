package com.example.back404.teamproject.dto.student.response;

import com.example.back404.teamproject.entity.Student;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class StudentInfoResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String studentNumber;

    public static StudentInfoResponseDto from(Student student) {
        return StudentInfoResponseDto.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .birthDate(student.getBirthDate())
                .studentNumber(student.getStudentNumber())
                .build();
    }
}