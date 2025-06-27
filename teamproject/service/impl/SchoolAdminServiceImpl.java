package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.school.request.ChangePasswordRequestDto;
import com.example.back404.teamproject.dto.school.request.SchoolInfoUpdateRequestDto;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.SchoolAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchoolAdminServiceImpl implements SchoolAdminService {

    private final SchoolRepository schoolRepository;
    private final PasswordEncoder passwordEncoder;

    private School getCurrentSchool() {
        // 실제 구현에서는 인증된 사용자 정보에서 schoolId 추출
        // 임시로 첫 번째 학교 반환
        return schoolRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));
    }

    @Override
    public ResponseDto<?> getAdminInfo() {
        try {
            School school = getCurrentSchool();
            return ResponseDto.setSuccess("관리자 정보 조회 성공", school);
        } catch (Exception e) {
            return ResponseDto.setFailed("관리자 정보 조회 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> updateAdminInfo(SchoolInfoUpdateRequestDto dto) {
        try {
            School school = getCurrentSchool();

            if (dto.getPhoneNumber() != null) {
                school.setSchoolAdminPhoneNumber(dto.getPhoneNumber());
            }
            if (dto.getEmail() != null) {
                school.setSchoolAdminEmail(dto.getEmail());
            }
            if (dto.getSchoolAddress() != null) {
                school.setSchoolAddress(dto.getSchoolAddress());
            }
            if (dto.getSchoolContactNumber() != null) {
                school.setSchoolContactNumber(dto.getSchoolContactNumber());
            }
            if (dto.getSchoolEmail() != null) {
                school.setSchoolEmail(dto.getSchoolEmail());
            }

            schoolRepository.save(school);
            return ResponseDto.setSuccess("관리자 정보 수정 완료", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("관리자 정보 수정 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> changePassword(ChangePasswordRequestDto dto) {
        try {
            School school = getCurrentSchool();

            if (!passwordEncoder.matches(dto.getCurrentPassword(), school.getSchoolPassword())) {
                return ResponseDto.setFailed("현재 비밀번호가 일치하지 않습니다.");
            }

            school.updatePassword(passwordEncoder.encode(dto.getNewPassword()));
            schoolRepository.save(school);

            return ResponseDto.setSuccess("비밀번호 변경 완료", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("비밀번호 변경 중 오류가 발생했습니다.");
        }
    }
}