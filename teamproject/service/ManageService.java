package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.student.request.StudentStatusUpdateRequestDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherStatusUpdateRequestDto;

public interface ManageService {
    ResponseDto<?> updateTeacherStatus(TeacherStatusUpdateRequestDto dto);
    ResponseDto<?> updateStudentStatus(StudentStatusUpdateRequestDto dto);
    ResponseDto<?> deleteTeacher(Long id);
    ResponseDto<?> deleteStudent(Long id);
}