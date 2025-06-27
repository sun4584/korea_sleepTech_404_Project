package com.example.back404.teamproject.service;

import com.example.back404.teamproject.dto.auth.request.LoginRequestDto;

public interface StudentAuthService {
    String login(LoginRequestDto request);
}