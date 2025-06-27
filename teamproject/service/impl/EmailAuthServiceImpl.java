package com.example.back404.teamproject.service.impl;

import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.service.EmailAuthService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailAuthServiceImpl implements EmailAuthService {

    private final JavaMailSender mailSender;
    private final JwtProvider jwtProvider;

    @Override
    public void sendVerificationLink(String email) {
        String token = jwtProvider.generateEmailToken(email);
        String link = "http://localhost:8080/api/auth/verify-email?token=" + token;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(email);
            helper.setSubject("이메일 인증 링크");
            helper.setText("이메일 인증을 위해 아래 링크를 클릭하세요:\n" + link);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송 실패", e);
        }
    }

    @Override
    public boolean verifyToken(String token) {
        return jwtProvider.validateToken(token);
    }
}