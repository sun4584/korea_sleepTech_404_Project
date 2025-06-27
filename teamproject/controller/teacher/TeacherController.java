package com.example.back404.teamproject.controller.teacher;

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
@RequestMapping("/api/v1/auth/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherAuthService teacherAuthService;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<?>> signUp(@Valid @RequestBody TeacherSignUpRequestDto requestDto) {
        ResponseDto<?> response = teacherService.signUp(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<?>> login(@Valid @RequestBody LoginRequestDto requestDto) {
        ResponseDto<?> response = teacherAuthService.login(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/find-id")
    public ResponseEntity<ResponseDto<FindIdResponseDto>> findId(@Valid @RequestBody TeacherFindIdRequestDto requestDto) {
        ResponseDto<FindIdResponseDto> response = teacherAuthService.findTeacherId(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/find-password")
    public ResponseEntity<ResponseDto<ResetPasswordResponseDto>> findPassword(@Valid @RequestBody TeacherResetPasswordRequestDto requestDto) {
        ResponseDto<ResetPasswordResponseDto> response = teacherAuthService.resetTeacherPassword(requestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseDto<TeacherInfoResponseDto>> getMyInfo(HttpServletRequest request) {
        String email = jwtProvider.getEmailFromRequest(request);
        ResponseDto<TeacherInfoResponseDto> response = teacherService.getTeacherInfo(email);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/me")
    public ResponseEntity<ResponseDto<?>> updateMyInfo(
            @Valid @RequestBody TeacherUpdateRequestDto requestDto,
            HttpServletRequest request) {
        String email = jwtProvider.getEmailFromRequest(request);
        ResponseDto<?> response = teacherService.updateTeacherInfo(email, requestDto);
        return ResponseEntity.ok(response);
    }
}
