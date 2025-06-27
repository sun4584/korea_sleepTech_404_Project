package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.auth.response.FindIdResponseDto;
import com.example.back404.teamproject.dto.auth.response.ResetPasswordResponseDto;
import com.example.back404.teamproject.dto.auth.request.StudentFindIdRequestDto;
import com.example.back404.teamproject.dto.auth.request.StudentResetPasswordRequestDto;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.repository.StudentRepository;
import com.example.back404.teamproject.service.StudentAuthExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentAuthExtensionServiceImpl implements StudentAuthExtensionService {

    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public ResponseDto<FindIdResponseDto> findStudentId(StudentFindIdRequestDto requestDto) {
        try {
            Student student = studentRepository.findByNameAndEmailAndStudentNumber(
                    requestDto.getName(),
                    requestDto.getEmail(),
                    requestDto.getStudentNumber())
                    .orElseThrow(() -> new IllegalArgumentException("일치하는 학생 정보를 찾을 수 없습니다."));

            String maskedUsername = maskUsername(student.getUsername());
            FindIdResponseDto response = new FindIdResponseDto(
                    "아이디 찾기 성공", maskedUsername);

            return ResponseDto.setSuccess("아이디 찾기 성공", response);

        } catch (Exception e) {
            return ResponseDto.setFailed("아이디 찾기 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto<ResetPasswordResponseDto> resetStudentPassword(StudentResetPasswordRequestDto requestDto) {
        try {
            Student student = studentRepository.findByUsernameAndEmailAndStudentNumber(
                    requestDto.getUsername(),
                    requestDto.getEmail(),
                    requestDto.getStudentNumber())
                    .orElseThrow(() -> new IllegalArgumentException("일치하는 학생 정보를 찾을 수 없습니다."));

            // 임시 비밀번호 생성
            String temporaryPassword = generateTemporaryPassword();
            student.setPassword(passwordEncoder.encode(temporaryPassword));
            studentRepository.save(student);

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