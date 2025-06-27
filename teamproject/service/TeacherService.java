package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherSignUpRequestDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherUpdateRequestDto;
import com.example.back404.teamproject.dto.teacher.response.TeacherInfoResponseDto;
import com.example.back404.teamproject.dto.teacher.response.TeacherListGetResponseDto;

import java.util.List;

public interface TeacherService {
    ResponseDto<?> signUp(TeacherSignUpRequestDto requestDto);
    ResponseDto<TeacherInfoResponseDto> getTeacherInfo(String email);
    ResponseDto<?> updateTeacherInfo(String email, TeacherUpdateRequestDto requestDto);
    ResponseDto<List<TeacherListGetResponseDto>> getTeacherList(String username);
}