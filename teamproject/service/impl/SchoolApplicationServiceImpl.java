package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.enums.SchoolStatus;
import com.example.back404.teamproject.dto.school.request.SchoolApplicationRequestDto;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.entity.SchoolApplication;
import com.example.back404.teamproject.repository.SchoolApplicationRepository;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.SchoolApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SchoolApplicationServiceImpl implements SchoolApplicationService {

    private final SchoolApplicationRepository applicationRepository;
    private final SchoolRepository schoolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseDto<?> register(SchoolApplicationRequestDto dto) {
        try {
            if (dto.getSchoolCode() == null) {
                return ResponseDto.setFailed("학교 코드가 비어있습니다.");
            }

            if (applicationRepository.existsBySchoolCode(dto.getSchoolCode())) {
                return ResponseDto.setFailed("이미 신청된 학교 코드입니다.");
            }

            if (schoolRepository.existsBySchoolCode(dto.getSchoolCode())) {
                return ResponseDto.setFailed("이미 등록된 학교 코드입니다.");
            }

            SchoolApplication application = SchoolApplication.builder()
                    .schoolName(dto.getSchoolName())
                    .schoolAddress(dto.getSchoolAddress())
                    .schoolContactNumber(dto.getSchoolContactNumber())
                    .schoolAdminUsername(dto.getSchoolAdminUsername())
                    .schoolAdminPassword(passwordEncoder.encode(dto.getSchoolAdminPassword()))
                    .schoolAdminName(dto.getSchoolAdminName())
                    .schoolAdminBirthDate(dto.getSchoolAdminBirthDate())
                    .schoolAdminPhoneNumber(dto.getSchoolAdminPhoneNumber())
                    .schoolAdminEmail(dto.getSchoolAdminEmail())
                    .schoolEmail(dto.getSchoolEmail())
                    .applicationStartedDay(dto.getApplicationStartedDay())
                    .applicationLimitedDay(dto.getApplicationLimitedDay())
                    .schoolCode(dto.getSchoolCode())
                    .status(SchoolStatus.PENDING)
                    .build();

            SchoolApplication saved = applicationRepository.save(application);
            return ResponseDto.setSuccess("학교 등록 신청 완료", saved.getSchoolApplicationId());

        } catch (Exception e) {
            return ResponseDto.setFailed("학교 등록 신청 처리 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> approve(Long id) {
        try {
            SchoolApplication application = applicationRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("신청서를 찾을 수 없습니다."));

            if (application.getStatus() != SchoolStatus.PENDING) {
                return ResponseDto.setFailed("승인 대기 상태가 아닙니다.");
            }

            // 학교 생성
            School school = School.builder()
                    .schoolName(application.getSchoolName())
                    .schoolAddress(application.getSchoolAddress())
                    .schoolContactNumber(application.getSchoolContactNumber())
                    .schoolCode(application.getSchoolCode())
                    .schoolPassword(application.getSchoolAdminPassword()) // 이미 암호화됨
                    .schoolAdminUsername(application.getSchoolAdminUsername())
                    .schoolAdminPassword(application.getSchoolAdminPassword()) // 이미 암호화됨
                    .schoolAdminName(application.getSchoolAdminName())
                    .schoolAdminPhoneNumber(application.getSchoolAdminPhoneNumber())
                    .schoolAdminEmail(application.getSchoolAdminEmail())
                    .schoolAdminBirthDate(LocalDate.parse(application.getSchoolAdminBirthDate()))
                    .applicationStartedDay(LocalDate.parse(application.getApplicationStartedDay()))
                    .applicationLimitedDay(LocalDate.parse(application.getApplicationLimitedDay()))
                    .status(SchoolStatus.APPROVED)
                    .schoolEmail(application.getSchoolEmail())
                    .build();

            schoolRepository.save(school);

            // 신청서 상태 업데이트
            application.updateStatus(SchoolStatus.APPROVED);
            applicationRepository.save(application);

            return ResponseDto.setSuccess("학교 신청 승인 완료", null);

        } catch (Exception e) {
            return ResponseDto.setFailed("학교 신청 승인 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> reject(Long id) {
        try {
            SchoolApplication application = applicationRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("신청서를 찾을 수 없습니다."));

            if (application.getStatus() != SchoolStatus.PENDING) {
                return ResponseDto.setFailed("승인 대기 상태가 아닙니다.");
            }

            application.updateStatus(SchoolStatus.REJECTED);
            applicationRepository.save(application);

            return ResponseDto.setSuccess("학교 신청 거절 완료", null);

        } catch (Exception e) {
            return ResponseDto.setFailed("학교 신청 거절 중 오류가 발생했습니다.");
        }
    }
}