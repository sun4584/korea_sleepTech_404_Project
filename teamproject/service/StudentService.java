package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.auth.request.LoginRequestDto;
import com.example.back404.teamproject.dto.student.request.StudentSignUpRequestDto;
import com.example.back404.teamproject.dto.student.request.StudentUpdateRequestDto;
import com.example.back404.teamproject.dto.student.response.StudentInfoResponseDto;
import com.example.back404.teamproject.entity.Student;

public interface StudentService {
    void signUp(StudentSignUpRequestDto request);
    Student login(LoginRequestDto request);
    StudentInfoResponseDto getStudentInfo(Long studentId);
    void updateStudentInfo(Long studentId, StudentUpdateRequestDto request);
}