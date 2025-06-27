package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.ResponseMessage;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.enums.SubjectStatus;
import com.example.back404.teamproject.dto.subject.response.SubjectGetResponseDto;
import com.example.back404.teamproject.dto.subject.response.SubjectListGetResponseDto;
import com.example.back404.teamproject.entity.Subject;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.repository.SubjectRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SchoolRepository schoolRepository;
    private final TeacherRepository teacherRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<SubjectListGetResponseDto>> searchSubjects(String userId, String subjectName, String subjectGrade, String subjectSemester, SubjectAffiliation subjectAffiliation) {
        try {
            boolean isAuthorized = schoolRepository.existsBySchoolAdminUsername(userId)
                    || teacherRepository.existsByTeacherUsername(userId);

            if (!isAuthorized) {
                throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + userId);
            }

            List<Subject> subjects = subjectRepository.searchSubjects(subjectName, subjectGrade, subjectSemester, subjectAffiliation);

            List<SubjectListGetResponseDto> responseDtos = subjects.stream()
                    .map(subject -> SubjectListGetResponseDto.builder()
                            .subjectId(subject.getSubjectId())
                            .subjectName(subject.getSubjectName())
                            .subjectGrade(subject.getSubjectGrade())
                            .subjectSemester(subject.getSubjectSemester())
                            .subjectAffiliation(subject.getSubjectAffiliation())
                            .build())
                    .collect(Collectors.toList());

            return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_LIST_SUCCESS, responseDtos);
        } catch (Exception e) {
            return ResponseDto.setFailed("과목 목록 조회 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<SubjectGetResponseDto> getSubjectById(String userId, String subjectId) {
        try {
            boolean isAuthorized = schoolRepository.existsBySchoolAdminUsername(userId)
                    || teacherRepository.existsByTeacherUsername(userId);

            if (!isAuthorized) {
                throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + userId);
            }

            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT + ": " + subjectId));

            SubjectGetResponseDto responseData = SubjectGetResponseDto.builder()
                    .subjectId(subject.getSubjectId())
                    .schoolId(subject.getSchool().getSchoolId())
                    .subjectName(subject.getSubjectName())
                    .subjectGrade(subject.getSubjectGrade())
                    .subjectSemester(subject.getSubjectSemester())
                    .subjectAffiliation(subject.getSubjectAffiliation())
                    .subjectStatus(subject.getSubjectStatus())
                    .subjectMaxEnrollment(subject.getSubjectMaxEnrollment())
                    .build();

            return ResponseDto.setSuccess(ResponseMessage.GET_SUBJECT_DETAIL_SUCCESS, responseData);
        } catch (EntityNotFoundException e) {
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            return ResponseDto.setFailed("과목 상세 정보 조회 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto<SubjectGetResponseDto> updateSubjectStatus(String username, String subjectId, SubjectStatus newStatus) {
        try {
            boolean isAuthorized = schoolRepository.existsBySchoolAdminUsername(username);

            if (!isAuthorized) {
                throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + username);
            }

            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new EntityNotFoundException(ResponseMessage.NOT_EXISTS_SUBJECT + ": " + subjectId));

            subject.updateStatus(newStatus);
            Subject updatedSubject = subjectRepository.save(subject);

            SubjectGetResponseDto responseData = SubjectGetResponseDto.builder()
                    .subjectId(updatedSubject.getSubjectId())
                    .schoolId(updatedSubject.getSchool().getSchoolId())
                    .subjectName(updatedSubject.getSubjectName())
                    .subjectGrade(updatedSubject.getSubjectGrade())
                    .subjectSemester(updatedSubject.getSubjectSemester())
                    .subjectAffiliation(updatedSubject.getSubjectAffiliation())
                    .subjectStatus(updatedSubject.getSubjectStatus())
                    .subjectMaxEnrollment(updatedSubject.getSubjectMaxEnrollment())
                    .build();

            return ResponseDto.setSuccess(ResponseMessage.UPDATE_SUBJECT_STATUS_SUCCESS, responseData);
        } catch (Exception e) {
            return ResponseDto.setFailed("과목 상태 변경 중 오류가 발생했습니다.");
        }
    }
}
