package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.common.SchoolListDto;
import com.example.back404.teamproject.dto.school.request.SchoolUpdateRequestDto;

import java.util.List;

public interface SchoolService {
    ResponseDto<?> getSchoolInfo(Long id);
    ResponseDto<?> updateSchoolInfo(Long id, SchoolUpdateRequestDto dto);
    ResponseDto<List<SchoolListDto>> getAllSchools();
}