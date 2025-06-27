package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.auth.request.LoginRequestDto;
import com.example.back404.teamproject.dto.auth.response.FindIdResponseDto;
import com.example.back404.teamproject.dto.auth.response.ResetPasswordResponseDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherFindIdRequestDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherResetPasswordRequestDto;

public interface TeacherAuthService {
    ResponseDto<?> login(LoginRequestDto requestDto);
    ResponseDto<FindIdResponseDto> findTeacherId(TeacherFindIdRequestDto requestDto);
    ResponseDto<ResetPasswordResponseDto> resetTeacherPassword(TeacherResetPasswordRequestDto requestDto);
}