package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.lecture.request.LectureCreateRequestDto;
import com.example.back404.teamproject.dto.lecture.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lecture.response.LectureDetailResponseDto;
import com.example.back404.teamproject.dto.lecture.response.LectureListDto;
import com.example.back404.teamproject.dto.lecture.response.LectureSimpleResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface LectureService {
    // 기존 메서드들
    List<LectureSimpleResponseDto> getAllLectures();
    LectureDetailResponseDto getLectureDetail(Long lectureId);
    ResponseDto<LectureListDto> updateLecture(Long lectureId, @Valid LectureUpdateRequestDto requestDto);
    ResponseDto<?> deleteLecture(Long lectureId);
    
    // 새로 추가된 메서드들
    ResponseDto<?> createLecture(LectureCreateRequestDto requestDto, String teacherEmail);
    List<LectureSimpleResponseDto> getLecturesByTeacher(String teacherEmail);
}