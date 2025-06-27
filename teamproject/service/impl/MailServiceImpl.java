package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.mail.request.SendMailRequestDto;
import com.example.back404.teamproject.entity.EmailVerification;
import com.example.back404.teamproject.repository.EmailVerificationRepository;
import com.example.back404.teamproject.service.MailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final JavaMailSender mailSender;

    @Override
    @Transactional
    public ResponseDto<?> sendVerificationMail(SendMailRequestDto dto) {
        try {
            // 기존 인증 정보가 있다면 삭제
            if (emailVerificationRepository.existsByEmail(dto.getEmail())) {
                emailVerificationRepository.deleteByEmail(dto.getEmail());
            }

            String token = UUID.randomUUID().toString();
            LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(30);

            EmailVerification verification = EmailVerification.builder()
                    .email(dto.getEmail())
                    .token(token)
                    .expiresAt(expiresAt)
                    .isVerified(false)
                    .build();

            emailVerificationRepository.save(verification);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(dto.getEmail());
            message.setSubject("이메일 인증 요청");
            message.setText("다음 링크를 클릭하여 이메일을 인증하세요:\n" +
                    "http://localhost:5173/email/verify?email=" + dto.getEmail() + "&token=" + token);

            mailSender.send(message);

            return ResponseDto.setSuccess("이메일 전송 완료", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("이메일 전송 중 오류가 발생했습니다.");
        }
    }

    @Override
    @Transactional
    public ResponseDto<?> verifyEmailToken(String email, String token) {
        try {
            EmailVerification verification = emailVerificationRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 인증 요청이 없습니다."));

            if (verification.isVerified()) {
                return ResponseDto.setFailed("이미 인증된 이메일입니다.");
            }

            if (!verification.getToken().equals(token)) {
                return ResponseDto.setFailed("유효하지 않은 토큰입니다.");
            }

            if (verification.getExpiresAt().isBefore(LocalDateTime.now())) {
                return ResponseDto.setFailed("만료된 토큰입니다.");
            }

            verification.setVerified(true);
            emailVerificationRepository.save(verification);

            return ResponseDto.setSuccess("이메일 인증 완료", null);
        } catch (Exception e) {
            return ResponseDto.setFailed("이메일 인증 중 오류가 발생했습니다.");
        }
    }
}