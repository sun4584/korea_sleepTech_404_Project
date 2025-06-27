package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.auth.request.SchoolSignUpRequestDto;
import com.example.back404.teamproject.dto.auth.request.UserSignInRequestDto;

public interface SchoolAuthService {
    ResponseDto<?> signUp(SchoolSignUpRequestDto dto);
    ResponseDto<?> signIn(UserSignInRequestDto dto);
    ResponseDto<?> changeAdmin(Long schoolId, SchoolSignUpRequestDto dto);
}