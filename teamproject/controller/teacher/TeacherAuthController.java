package com.example.back404.teamproject.controller.teacher;

import com.example.back404.teamproject.common.ApiMappingPattern;
import com.example.back404.teamproject.common.ResponseDto;
import com.example.back404.teamproject.dto.auth.request.LoginRequestDto;
import com.example.back404.teamproject.dto.auth.response.FindIdResponseDto;
import com.example.back404.teamproject.dto.auth.response.ResetPasswordResponseDto;
import com.example.back404.teamproject.dto.teacher.request.*;
import com.example.back404.teamproject.dto.teacher.response.TeacherInfoResponseDto;
import com.example.back404.teamproject.provider.JwtProvider;
import com.example.back404.teamproject.service.TeacherAuthService;
import com.example.back404.teamproject.service.TeacherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TeacherAuthController {

    private final TeacherService teacherService;
    private final TeacherAuthService teacherAuthService;
    private final JwtProvider jwtProvider;

    // ========== 기존 메서드들 (ApiMappingPattern 사용) ========== //
    @PostMapping(ApiMappingPattern.API_AUTH_TEACHER + "/signup")
    public ResponseEntity<ResponseDto<?>> signUp(@Valid @RequestBody TeacherSignUpRequestDto requestDto) {
        ResponseDto<?> response = teacherService.signUp(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ApiMappingPattern.API_AUTH_TEACHER + "/login")
    public ResponseEntity<ResponseDto<?>> login(@Valid @RequestBody LoginRequestDto requestDto) {
        ResponseDto<?> response = teacherAuthService.login(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ApiMappingPattern.API_AUTH_TEACHER + "/find-id")
    public ResponseEntity<ResponseDto<FindIdResponseDto>> findId(@Valid @RequestBody TeacherFindIdRequestDto requestDto) {
        ResponseDto<FindIdResponseDto> response = teacherAuthService.findTeacherId(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(ApiMappingPattern.API_AUTH_TEACHER + "/find-password")
    public ResponseEntity<ResponseDto<ResetPasswordResponseDto>> findPassword(@Valid @RequestBody TeacherResetPasswordRequestDto requestDto) {
        ResponseDto<ResetPasswordResponseDto> response = teacherAuthService.resetTeacherPassword(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping(ApiMappingPattern.API_AUTH_TEACHER + "/me")
    public ResponseEntity<ResponseDto<TeacherInfoResponseDto>> getMyInfo(HttpServletRequest request) {
        String email = jwtProvider.getEmailFromRequest(request);
        ResponseDto<TeacherInfoResponseDto> response = teacherService.getTeacherInfo(email);
        return ResponseEntity.ok(response);
    }

    @PutMapping(ApiMappingPattern.API_AUTH_TEACHER + "/me")
    public ResponseEntity<ResponseDto<?>> updateMyInfo(
            @Valid @RequestBody TeacherUpdateRequestDto requestDto,
            HttpServletRequest request) {
        String email = jwtProvider.getEmailFromRequest(request);
        ResponseDto<?> response = teacherService.updateTeacherInfo(email, requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/teacher/signup")
    public ResponseEntity<ResponseDto<?>> teacherSignup(@Valid @RequestBody TeacherSignUpRequestDto requestDto) {
        ResponseDto<?> response = teacherService.signUp(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/teacher/login")
    public ResponseEntity<ResponseDto<?>> teacherLogin(@Valid @RequestBody LoginRequestDto requestDto) {
        ResponseDto<?> response = teacherAuthService.login(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/teacher/find-id")
    public ResponseEntity<ResponseDto<FindIdResponseDto>> teacherFindId(@Valid @RequestBody TeacherFindIdRequestDto requestDto) {
        ResponseDto<FindIdResponseDto> response = teacherAuthService.findTeacherId(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/teacher/find-password")
    public ResponseEntity<ResponseDto<ResetPasswordResponseDto>> teacherFindPassword(@Valid @RequestBody TeacherResetPasswordRequestDto requestDto) {
        ResponseDto<ResetPasswordResponseDto> response = teacherAuthService.resetTeacherPassword(requestDto);
        return ResponseEntity.ok(response);
    }
}