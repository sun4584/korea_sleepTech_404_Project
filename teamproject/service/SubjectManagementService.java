package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.subject.request.SubjectCreateRequestDto;
import com.example.back404.teamproject.dto.subject.request.SubjectUpdateRequestDto;

public interface SubjectManagementService {
    ResponseDto<?> createSubject(SubjectCreateRequestDto requestDto, String teacherEmail);
    ResponseDto<?> updateSubject(String subjectId, SubjectUpdateRequestDto requestDto, String teacherEmail);
    ResponseDto<?> deleteSubject(String subjectId, String teacherEmail);
}