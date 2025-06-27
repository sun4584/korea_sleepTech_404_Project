package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.enums.SchoolStatus;
import com.example.back404.teamproject.dto.auth.request.SchoolSignUpRequestDto;
import com.example.back404.teamproject.dto.auth.request.UserSignInRequestDto;
import com.example.back404.teamproject.dto.auth.response.UserSignInResponseDto;
import com.example.back404.teamproject.entity.EmailVerification;
import com.example.back404.teamproject.entity.School;
import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.repository.EmailVerificationRepository;
import com.example.back404.teamproject.repository.SchoolRepository;
import com.example.back404.teamproject.service.SchoolAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SchoolAuthServiceImpl implements SchoolAuthService {

    private final SchoolRepository schoolRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public ResponseDto<?> signUp(SchoolSignUpRequestDto dto) {
        try {
            // 이메일 인증 확인
            EmailVerification emailVerification = emailVerificationRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("이메일 인증 내역이 없습니다."));

            if (!emailVerification.isVerified()) {
                return ResponseDto.setFailed("이메일 인증을 먼저 완료해주세요.");
            }

            // 중복 체크
            if (schoolRepository.existsBySchoolCode(dto.getSchoolCode())) {
                return ResponseDto.setFailed("이미 등록된 학교 코드입니다.");
            }

            if (schoolRepository.existsBySchoolAdminEmail(dto.getEmail())) {
                return ResponseDto.setFailed("이미 등록된 이메일입니다.");
            }

            // 학교 생성
            School school = School.builder()
                    .schoolCode(dto.getSchoolCode())
                    .schoolAdminUsername(dto.getUsername())
                    .schoolPassword(passwordEncoder.encode(dto.getPassword()))
                    .schoolAdminPassword(passwordEncoder.encode(dto.getPassword()))
                    .schoolAdminName(dto.getName())
                    .schoolAdminEmail(dto.getEmail())
                    .schoolAdminBirthDate(LocalDate.parse(dto.getBirthDate()))
                    .schoolAdminPhoneNumber(dto.getPhoneNumber())
                    .status(SchoolStatus.APPROVED)
                    .schoolName("기본학교명")
                    .schoolAddress("기본주소")
                    .schoolContactNumber("000-0000-0000")
                    .schoolEmail(dto.getEmail())
                    .applicationStartedDay(LocalDate.now())
                    .applicationLimitedDay(LocalDate.now().plusDays(30))
                    .build();

            schoolRepository.save(school);
            return ResponseDto.setSuccess("관리자 회원가입 성공", null);

        } catch (Exception e) {
            return ResponseDto.setFailed("회원가입 처리 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto<?> signIn(UserSignInRequestDto dto) {
        try {
            School school = schoolRepository.findBySchoolCode(dto.getSchoolCode())
                    .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));

            if (!passwordEncoder.matches(dto.getPassword(), school.getSchoolPassword())) {
                return ResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
            }

            if (school.getStatus() != SchoolStatus.APPROVED) {
                return ResponseDto.setFailed("승인되지 않은 학교입니다.");
            }

            String token = jwtProvider.generateToken(school.getSchoolAdminEmail(), "ADMIN");
            UserSignInResponseDto responseData = new UserSignInResponseDto(token, jwtProvider.getExpiration());

            return ResponseDto.setSuccess("로그인 성공", responseData);

        } catch (Exception e) {
            return ResponseDto.setFailed("로그인 처리 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> changeAdmin(Long schoolId, SchoolSignUpRequestDto dto) {
        try {
            School school = schoolRepository.findById(schoolId)
                    .orElseThrow(() -> new IllegalArgumentException("학교를 찾을 수 없습니다."));

            // 이메일 인증 확인
            EmailVerification emailVerification = emailVerificationRepository.findByEmail(dto.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("이메일 인증 내역이 없습니다."));

            if (!emailVerification.isVerified()) {
                return ResponseDto.setFailed("이메일 인증을 먼저 완료해주세요.");
            }

            // 관리자 정보 업데이트
            school.setSchoolAdminEmail(dto.getEmail());
            school.setSchoolAdminPhoneNumber(dto.getPhoneNumber());
            school.updatePassword(passwordEncoder.encode(dto.getPassword()));

            schoolRepository.save(school);
            return ResponseDto.setSuccess("관리자 변경 완료", null);

        } catch (Exception e) {
            return ResponseDto.setFailed("관리자 변경 중 오류가 발생했습니다.");
        }
    }
}