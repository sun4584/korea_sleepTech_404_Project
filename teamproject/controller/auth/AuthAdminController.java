package com.example.back404.teamproject.controller.auth;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthAdminController {

    @PostMapping("/admin/signup")
    public ResponseEntity<?> adminSignup(@RequestBody Object dto) {
        return ResponseEntity.ok("관리자 회원가입");
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody Object dto) {
        return ResponseEntity.ok("관리자 로그인");
    }

    @PostMapping("/admin/find-id")
    public ResponseEntity<?> adminFindId(@RequestBody Object dto) {
        return ResponseEntity.ok("관리자 아이디 찾기");
    }

    @PostMapping("/admin/find-password")
    public ResponseEntity<?> adminFindPassword(@RequestBody Object dto) {
        return ResponseEntity.ok("관리자 비밀번호 찾기");
    }
}
