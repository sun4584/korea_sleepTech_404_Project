package com.example.back404.teamproject.controller.auth;

import com.example.back404.teamproject.dto.auth.request.EmailSendRequestDto;
import com.example.back404.teamproject.service.EmailAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class EmailAuthController {

    private final EmailAuthService emailAuthService;

    @PostMapping("/send-code")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailSendRequestDto request) {
        emailAuthService.sendVerificationLink(request.getEmail());
        return ResponseEntity.ok("인증 링크가 이메일로 전송되었습니다.");
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        boolean result = emailAuthService.verifyToken(token);
        return result
                ? ResponseEntity.ok("이메일 인증이 완료되었습니다.")
                : ResponseEntity.badRequest().body("유효하지 않은 인증 링크입니다.");
    }
}