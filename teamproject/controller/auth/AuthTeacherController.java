package com.example.back404.teamproject.controller.auth;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthTeacherController {

    @PostMapping("/teacher/signup")
    public ResponseEntity<?> teacherSignup(@RequestBody Object dto) {
        return ResponseEntity.ok("교사 회원가입");
    }

    @PostMapping("/teacher/login")
    public ResponseEntity<?> teacherLogin(@RequestBody Object dto) {
        return ResponseEntity.ok("교사 로그인");
    }

    @PostMapping("/teacher/find-id")
    public ResponseEntity<?> teacherFindId(@RequestBody Object dto) {
        return ResponseEntity.ok("교사 아이디 찾기");
    }

    @PostMapping("/teacher/find-password")
    public ResponseEntity<?> teacherFindPassword(@RequestBody Object dto) {
        return ResponseEntity.ok("교사 비밀번호 찾기");
    }
}
