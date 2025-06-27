package com.example.back404.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email-verifications")
@RequiredArgsConstructor
public class MailController {

    // POST /api/v1/email-verifications
    @PostMapping
    public ResponseEntity<?> sendEmailVerification(@RequestBody Object dto) {
        return ResponseEntity.ok("이메일 인증 전송");
    }

    // POST /api/v1/email-verifications/confirm
    @PostMapping("/confirm")
    public ResponseEntity<?> confirmEmailVerification(@RequestBody Object dto) {
        return ResponseEntity.ok("이메일 인증 확인");
    }
}