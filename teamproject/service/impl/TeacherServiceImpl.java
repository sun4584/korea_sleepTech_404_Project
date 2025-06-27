package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.ResponseMessage;
import com.example.back404.teamproject.common.enums.TeacherStatus;
import com.example.back404.teamproject.dto.teacher.request.TeacherSignUpRequestDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherUpdateRequestDto;
import com.example.back404.teamproject.dto.teacher.response.TeacherInfoResponseDto;
import com.example.back404.teamproject.dto.teacher.response.TeacherListGetResponseDto;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseDto<?> signUp(TeacherSignUpRequestDto requestDto) {
        try {
            if (teacherRepository.existsByUsername(requestDto.getUsername())) {
                return ResponseDto.setFailed("이미 사용 중인 사용자명입니다.");
            }

            if (teacherRepository.existsByEmail(requestDto.getEmail())) {
                return ResponseDto.setFailed("이미 등록된 이메일입니다.");
            }

            School school = schoolRepository.findBySchoolCode(requestDto.getSchoolCode())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학교 코드입니다."));

            Teacher teacher = Teacher.builder()
                    .teacherId(generateTeacherId())
                    .teacherUsername(requestDto.getUsername())
                    .teacherPassword(passwordEncoder.encode(requestDto.getPassword()))
                    .teacherName(requestDto.getName())
                    .teacherEmail(requestDto.getEmail())
                    .teacherPhoneNumber(requestDto.getPhoneNumber())
                    .teacherSubject(requestDto.getSubject())
                    .teacherBirthDate(LocalDate.parse(requestDto.getBirthDate()))
                    .school(school)
                    .teacherStatus(TeacherStatus.PENDING)
                    .build();

            teacherRepository.save(teacher);
            return ResponseDto.setSuccess("교사 회원가입이 완료되었습니다. 관리자 승인을 기다려주세요.", null);

        } catch (Exception e) {
            return ResponseDto.setFailed("회원가입 처리 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<TeacherInfoResponseDto> getTeacherInfo(String email) {
        try {
            Teacher teacher = teacherRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("교사 정보를 찾을 수 없습니다."));

            TeacherInfoResponseDto response = TeacherInfoResponseDto.builder()
                    .teacherId(teacher.getTeacherId())
                    .teacherName(teacher.getTeacherName())
                    .teacherEmail(teacher.getTeacherEmail())
                    .teacherPhoneNumber(teacher.getTeacherPhoneNumber())
                    .teacherUsername(teacher.getTeacherUsername())
                    .teacherSubject(teacher.getTeacherSubject())
                    .teacherStatus(teacher.getTeacherStatus())
                    .createdAt(teacher.getCreatedAt())
                    .build();

            return ResponseDto.setSuccess("교사 정보 조회 성공", response);
        } catch (Exception e) {
            return ResponseDto.setFailed("교사 정보 조회 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto<?> updateTeacherInfo(String email, TeacherUpdateRequestDto requestDto) {
        try {
            Teacher teacher = teacherRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("교사 정보를 찾을 수 없습니다."));

            if (!requestDto.getEmail().equals(teacher.getTeacherEmail()) && 
                teacherRepository.existsByEmail(requestDto.getEmail())) {
                return ResponseDto.setFailed("이미 사용 중인 이메일입니다.");
            }

            teacher.update(
                    requestDto.getName(),
                    requestDto.getEmail(),
                    requestDto.getPhoneNumber(),
                    requestDto.getSubject()
            );

            teacherRepository.save(teacher);
            return ResponseDto.setSuccess("교사 정보 수정 성공", null);

        } catch (Exception e) {
            return ResponseDto.setFailed("교사 정보 수정 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<List<TeacherListGetResponseDto>> getTeacherList(String username) {
        boolean isAuthorized = schoolRepository.existsBySchoolAdminUsername(username)
                || teacherRepository.existsByUsername(username);

        if (!isAuthorized) {
            throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + username);
        }

        List<Teacher> teachers = teacherRepository.findAll();

        List<TeacherListGetResponseDto> responseData = teachers.stream()
                .map(teacher -> TeacherListGetResponseDto.builder()
                        .teacherId(teacher.getTeacherId())
                        .teacherUsername(teacher.getTeacherUsername())
                        .teacherName(teacher.getTeacherName())
                        .teacherEmail(teacher.getTeacherEmail())
                        .teacherPhoneNumber(teacher.getTeacherPhoneNumber())
                        .teacherSubject(teacher.getTeacherSubject())
                        .teacherStatus(teacher.getTeacherStatus())
                        .build())
                .collect(Collectors.toList());

        return ResponseDto.setSuccess(ResponseMessage.GET_TEACHER_LIST_SUCCESS, responseData);
    }

    private String generateTeacherId() {
        return "T" + System.currentTimeMillis();
    }
}