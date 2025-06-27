package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.common.enums.TeacherStatus;
import com.example.back404.teamproject.dto.auth.request.LoginRequestDto;
import com.example.back404.teamproject.dto.auth.response.FindIdResponseDto;
import com.example.back404.teamproject.dto.auth.response.ResetPasswordResponseDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherFindIdRequestDto;
import com.example.back404.teamproject.dto.teacher.request.TeacherResetPasswordRequestDto;
import com.example.back404.teamproject.entity.Teacher;
import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.repository.TeacherRepository;
import com.example.back404.teamproject.service.TeacherAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherAuthServiceImpl implements TeacherAuthService {

    private final TeacherRepository teacherRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseDto<?> login(LoginRequestDto requestDto) {
        try {
            Teacher teacher = teacherRepository.findByUsername(requestDto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자명입니다."));

            if (!passwordEncoder.matches(requestDto.getPassword(), teacher.getPassword())) {
                return ResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
            }

            if (teacher.getStatus() != TeacherStatus.APPROVED) {
                return ResponseDto.setFailed("승인되지 않은 계정입니다. 관리자에게 문의하세요.");
            }

            String token = jwtProvider.generateToken(teacher.getEmail(), "TEACHER");
            return ResponseDto.setSuccess("로그인 성공", token);

        } catch (Exception e) {
            return ResponseDto.setFailed("로그인 처리 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<FindIdResponseDto> findTeacherId(TeacherFindIdRequestDto requestDto) {
        try {
            Teacher teacher = teacherRepository.findByNameAndEmailAndPhoneNumber(
                    requestDto.getName(), 
                    requestDto.getEmail(), 
                    requestDto.getPhoneNumber())
                    .orElseThrow(() -> new IllegalArgumentException("일치하는 교사 정보를 찾을 수 없습니다."));

            String maskedUsername = maskUsername(teacher.getUsername());
            FindIdResponseDto response = new FindIdResponseDto(
                    "아이디 찾기 성공", maskedUsername);

            return ResponseDto.setSuccess("아이디 찾기 성공", response);

        } catch (Exception e) {
            return ResponseDto.setFailed("아이디 찾기 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto<ResetPasswordResponseDto> resetTeacherPassword(TeacherResetPasswordRequestDto requestDto) {
        try {
            Teacher teacher = teacherRepository.findByUsernameAndEmailAndName(
                    requestDto.getUsername(),
                    requestDto.getEmail(),
                    requestDto.getName())
                    .orElseThrow(() -> new IllegalArgumentException("일치하는 교사 정보를 찾을 수 없습니다."));

            // 임시 비밀번호 생성
            String temporaryPassword = generateTemporaryPassword();
            teacher.setPassword(passwordEncoder.encode(temporaryPassword));
            teacherRepository.save(teacher);

            ResetPasswordResponseDto response = new ResetPasswordResponseDto(
                    "임시 비밀번호가 발급되었습니다.", temporaryPassword);

            return ResponseDto.setSuccess("비밀번호 재설정 성공", response);

        } catch (Exception e) {
            return ResponseDto.setFailed("비밀번호 재설정 중 오류가 발생했습니다.");
        }
    }

    private String maskUsername(String username) {
        if (username.length() <= 2) {
            return username;
        }
        StringBuilder masked = new StringBuilder();
        masked.append(username.charAt(0));
        for (int i = 1; i < username.length() - 1; i++) {
            masked.append("*");
        }
        masked.append(username.charAt(username.length() - 1));
        return masked.toString();
    }

    private String generateTemporaryPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }
}