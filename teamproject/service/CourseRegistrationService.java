package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.registration.request.CourseRegistrationRequestDto;
import com.example.back404.teamproject.dto.registration.response.CourseRegistrationResponseDto;
import com.example.back404.teamproject.dto.registration.response.EnrolledStudentListDto;
import com.example.back404.teamproject.dto.registration.response.LectureRegistrationSummaryDto;

import java.util.List;

public interface CourseRegistrationService {
    void registerByEmail(String email, CourseRegistrationRequestDto requestDto);
    List<CourseRegistrationResponseDto> getMyRegistrations(String email);
    CourseRegistrationResponseDto getRegistrationDetail(String email, Long registrationId);
    void cancelRegistration(String email, Long registrationId);
    
    // 관리 기능 추가
    ResponseDto<LectureRegistrationSummaryDto> getRegisteredStudentsByLectureId(String username, Long lectureId);
    ResponseDto<List<EnrolledStudentListDto>> getEnrolledStudentsByLectureId(String username, Long lectureId);
}