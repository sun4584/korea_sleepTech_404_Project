package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.dto.student.response.StudentGetResponseDto;
import com.example.back404.teamproject.dto.student.response.StudentListGetResponseDto;

import java.util.List;

public interface StudentManageService {
    ResponseDto<StudentGetResponseDto> findByStudentId(String username, String studentId);
    ResponseDto<List<StudentListGetResponseDto>> searchStudents(
            String username, String studentNumber, String studentName, 
            String studentGrade, SubjectAffiliation studentAffiliation);
}