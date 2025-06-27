package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.auth.request.SchoolSignUpRequestDto;
import com.example.back404.teamproject.dto.auth.request.UserSignInRequestDto;
import com.example.back404.teamproject.service.SchoolAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final SchoolAuthService schoolAuthService;

    // ========== 기존 메서드들 (ApiMappingPattern 사용) ========== //
    @PostMapping("/api/v1/auth/admin/signup")
    public ResponseEntity<ResponseDto<?>> signUp(@RequestBody @Valid SchoolSignUpRequestDto dto) {
        return ResponseEntity.ok(schoolAuthService.signUp(dto));
    }

    @PostMapping("/api/v1/auth/admin/signin")
    public ResponseEntity<ResponseDto<?>> signIn(@RequestBody @Valid UserSignInRequestDto dto) {
        return ResponseEntity.ok(schoolAuthService.signIn(dto));
    }

    @PutMapping("/api/v1/auth/admin/change")
    public ResponseEntity<ResponseDto<?>> changeAdmin(@RequestParam Long schoolId,
                                         @RequestBody @Valid SchoolSignUpRequestDto newAdminDto) {
        return ResponseEntity.ok(schoolAuthService.changeAdmin(schoolId, newAdminDto));
    }

    // ========== 새로 추가된 경로들 ========== //
    @PostMapping("/auth/admin/signup")
    public ResponseEntity<ResponseDto<?>> adminSignup(@RequestBody @Valid SchoolSignUpRequestDto dto) {
        return ResponseEntity.ok(schoolAuthService.signUp(dto));
    }

    @PostMapping("/auth/admin/login")
    public ResponseEntity<ResponseDto<?>> adminLogin(@RequestBody @Valid UserSignInRequestDto dto) {
        return ResponseEntity.ok(schoolAuthService.signIn(dto));
    }

    @PostMapping("/auth/admin/find-id")
    public ResponseEntity<ResponseDto<?>> adminFindId(@RequestBody Object dto) {
        // 관리자 아이디 찾기 로직 구현 필요
        return ResponseEntity.ok(ResponseDto.setSuccess("관리자 아이디 찾기"));
    }

    @PostMapping("/auth/admin/find-password")
    public ResponseEntity<ResponseDto<?>> adminFindPassword(@RequestBody Object dto) {
        // 관리자 비밀번호 찾기 로직 구현 필요
        return ResponseEntity.ok(ResponseDto.setSuccess("관리자 비밀번호 찾기"));
    }
}