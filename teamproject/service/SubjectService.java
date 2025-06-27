package com.example.back404.teamproject.service;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.enums.SubjectStatus;
import com.example.back404.teamproject.dto.subject.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectListGetResponseDto;

import java.util.List;

public interface SubjectService {
    ResponseDto<List<SubjectListGetResponseDto>> searchSubjects(String userId, String subjectName, String grade, String semester, SubjectAffiliation affiliation);
    ResponseDto<SubjectGetResponseDto> getSubjectById(String userId, String subjectId);
    ResponseDto<SubjectGetResponseDto> updateSubjectStatus(String username, String subjectId, SubjectStatus newStatus);
}