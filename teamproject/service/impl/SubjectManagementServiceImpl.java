package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.ResponseMessage;
import com.example.back404.teamproject.common.enums.SubjectStatus;
import com.example.back404.teamproject.dto.subject.request.SubjectCreateRequestDto;
import com.example.back404.teamproject.dto.subject.request.SubjectUpdateRequestDto;
import com.example.back404.teamproject.entity.Lecture;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.entity.Subject;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.repository.LectureRepository;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.repository.SubjectRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.SubjectManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectManagementServiceImpl implements SubjectManagementService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;
    private final LectureRepository lectureRepository;

    @Override
    public ResponseDto<?> createSubject(SubjectCreateRequestDto requestDto, String teacherEmail) {
        try {
            // 교사 정보 조회
            Teacher teacher = teacherRepository.findByTeacherEmail(teacherEmail)
                    .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_TEACHER));

            // 학교 정보 조회 (교사의 학교)
            School school = teacher.getSchool();
            if (school == null) {
                return ResponseDto.setFailed("교사의 학교 정보를 찾을 수 없습니다.");
            }

            // 과목 ID 중복 체크 (같은 학교 내에서)
            if (subjectRepository.existsBySubjectIdAndSchool(requestDto.getSubjectId(), school)) {
                return ResponseDto.setFailed(ResponseMessage.ALREADY_EXISTS_SUBJECT_ID);
            }

            // 과목 생성
            Subject subject = Subject.builder()
                    .subjectId(requestDto.getSubjectId())
                    .school(school)
                    .teacher(teacher)
                    .subjectName(requestDto.getSubjectName())
                    .subjectGrade(requestDto.getGrade())
                    .subjectSemester(requestDto.getSemester())
                    .subjectAffiliation(requestDto.getAffiliation())
                    .subjectStatus(SubjectStatus.PENDING)
                    .subjectMaxEnrollment(requestDto.getMaxEnrollment())
                    .subjectDescription(requestDto.getDescription())
                    .build();

            subjectRepository.save(subject);
            return ResponseDto.setSuccess(ResponseMessage.CREATE_SUBJECT_SUCCESS);

        } catch (IllegalArgumentException e) {
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            return ResponseDto.setFailed("과목 등록 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<?> updateSubject(String subjectId, SubjectUpdateRequestDto requestDto, String teacherEmail) {
        try {
            // 과목 존재 여부 확인
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_SUBJECT));

            // 교사 정보 조회
            Teacher teacher = teacherRepository.findByTeacherEmail(teacherEmail)
                    .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_TEACHER));

            // 과목 담당 교사 권한 확인
            if (!subject.getTeacher().getTeacherId().equals(teacher.getTeacherId())) {
                return ResponseDto.setFailed(ResponseMessage.NO_AUTHORITY);
            }

            // 승인 대기 상태의 과목만 수정 가능
            if (subject.getSubjectStatus() != SubjectStatus.PENDING) {
                return ResponseDto.setFailed(ResponseMessage.CANNOT_PROCESS_PENDING_ONLY);
            }

            // 과목 정보 업데이트
            subject.updateInfo(
                    requestDto.getSubjectName(),
                    requestDto.getGrade(),
                    requestDto.getSemester(),
                    requestDto.getAffiliation(),
                    requestDto.getMaxEnrollment()
            );

            // 상태를 UPDATED로 변경
            subject.updateStatus(SubjectStatus.UPDATED);

            subjectRepository.save(subject);
            return ResponseDto.setSuccess(ResponseMessage.UPDATE_SUBJECT_SUCCESS);

        } catch (IllegalArgumentException e) {
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            return ResponseDto.setFailed("과목 수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<?> deleteSubject(String subjectId, String teacherEmail) {
        try {
            // 과목 존재 여부 확인
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_SUBJECT));

            // 교사 정보 조회
            Teacher teacher = teacherRepository.findByTeacherEmail(teacherEmail)
                    .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_TEACHER));

            // 과목 담당 교사 권한 확인
            if (!subject.getTeacher().getTeacherId().equals(teacher.getTeacherId())) {
                return ResponseDto.setFailed(ResponseMessage.NO_AUTHORITY);
            }

            // 해당 과목으로 개설된 강의가 있는지 확인
            List<Lecture> lectures = lectureRepository.findBySubject_SubjectId(subjectId);
            if (!lectures.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.CANNOT_DELETE_SUBJECT_HAS_LECTURE);
            }

            // 승인 대기 상태의 과목만 삭제 가능
            if (subject.getSubjectStatus() != SubjectStatus.PENDING) {
                return ResponseDto.setFailed(ResponseMessage.CANNOT_PROCESS_PENDING_ONLY);
            }

            // 과목 삭제
            subjectRepository.delete(subject);
            return ResponseDto.setSuccess(ResponseMessage.DELETE_SUBJECT_SUCCESS);

        } catch (IllegalArgumentException e) {
            return ResponseDto.setFailed(e.getMessage());
        } catch (Exception e) {
            return ResponseDto.setFailed("과목 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 과목 상태별 조회 (추가 기능)
     */
    @Transactional(readOnly = true)
    public ResponseDto<?> getSubjectsByTeacher(String teacherEmail) {
        try {
            Teacher teacher = teacherRepository.findByTeacherEmail(teacherEmail)
                    .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_TEACHER));

            List<Subject> subjects = subjectRepository.findByTeacher_TeacherId(teacher.getTeacherId());
            return ResponseDto.setSuccess("교사 과목 목록 조회 성공", subjects);

        } catch (Exception e) {
            return ResponseDto.setFailed("과목 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * 승인 대기 중인 과목 목록 조회 (관리자용)
     */
    @Transactional(readOnly = true)
    public ResponseDto<?> getPendingSubjects(String adminEmail) {
        try {
            // 관리자 권한 확인 - schoolAdminEmail로 조회
            School school = schoolRepository.findBySchoolAdminEmail(adminEmail)
                    .orElseThrow(() -> new IllegalArgumentException("관리자 정보를 찾을 수 없습니다."));
            
            List<Subject> pendingSubjects = subjectRepository.findBySchoolAndSubjectStatus(school, SubjectStatus.PENDING);
            return ResponseDto.setSuccess("승인 대기 과목 목록 조회 성공", pendingSubjects);

        } catch (Exception e) {
            return ResponseDto.setFailed("승인 대기 과목 목록 조회 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}