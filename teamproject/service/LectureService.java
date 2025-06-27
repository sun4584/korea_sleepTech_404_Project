package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.lecture.request.LectureUpdateRequestDto;
import com.example.back404.teamproject.dto.lecture.response.LectureDetailResponseDto;
import com.example.back404.teamproject.dto.lecture.response.LectureListDto;
import com.example.back404.teamproject.dto.lecture.response.LectureSimpleResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface LectureService {
    List<LectureSimpleResponseDto> getAllLectures();
    LectureDetailResponseDto getLectureDetail(Long lectureId);
    ResponseDto<LectureListDto> updateLecture(Long lectureId, @Valid LectureUpdateRequestDto requestDto);
    ResponseDto<?> deleteLecture(Long lectureId);
}