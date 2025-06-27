package com.example.back404.teamproject.controller.auth;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthStudentController {

    @PostMapping("/student/signup")
    public ResponseEntity<?> studentSignup(@RequestBody Object dto) {
        return ResponseEntity.ok("학생 회원가입");
    }

    @PostMapping("/student/login")
    public ResponseEntity<?> studentLogin(@RequestBody Object dto) {
        return ResponseEntity.ok("학생 로그인");
    }

    @PostMapping("/student/find-id")
    public ResponseEntity<?> studentFindId(@RequestBody Object dto) {
        return ResponseEntity.ok("학생 아이디 찾기");
    }

    @PostMapping("/student/find-password")
    public ResponseEntity<?> studentFindPassword(@RequestBody Object dto) {
        return ResponseEntity.ok("학생 비밀번호 찾기");
    }
}