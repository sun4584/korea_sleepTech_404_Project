package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.school.request.SchoolApplicationRequestDto;

public interface SchoolApplicationService {
    ResponseDto<?> register(SchoolApplicationRequestDto dto);
    ResponseDto<?> approve(Long id);
    ResponseDto<?> reject(Long id);
}
