package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.auth.response.FindIdResponseDto;
import com.example.back404.teamproject.dto.auth.response.ResetPasswordResponseDto;
import com.example.back404.teamproject.dto.auth.request.StudentFindIdRequestDto;
import com.example.back404.teamproject.dto.auth.request.StudentResetPasswordRequestDto;

public interface StudentAuthExtensionService {
    ResponseDto<FindIdResponseDto> findStudentId(StudentFindIdRequestDto requestDto);
    ResponseDto<ResetPasswordResponseDto> resetStudentPassword(StudentResetPasswordRequestDto requestDto);
}