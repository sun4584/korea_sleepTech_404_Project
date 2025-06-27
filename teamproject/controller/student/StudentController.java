package com.example.back404.teamproject.controller.student;

import com.example.back404.teamproject.dto.auth.request.LoginRequestDto;
import com.example.back404.teamproject.dto.student.request.StudentSignUpRequestDto;
import com.example.back404.teamproject.dto.student.request.StudentUpdateRequestDto;
import com.example.back404.teamproject.dto.student.response.StudentInfoResponseDto;
import com.example.back404.teamproject.entity.Student;
import com.example.back404.teamproject.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody StudentSignUpRequestDto request) {
        studentService.signUp(request);
        return ResponseEntity.ok("학생 회원가입이 완료되었습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto request) {
        Student student = studentService.login(request);
        return ResponseEntity.ok("로그인 성공: " + student.getName());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentInfoResponseDto> getStudentInfo(@PathVariable Long studentId) {
        StudentInfoResponseDto info = studentService.getStudentInfo(studentId);
        return ResponseEntity.ok(info);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<String> updateStudentInfo(
            @PathVariable Long studentId,
            @Valid @RequestBody StudentUpdateRequestDto request) {
        studentService.updateStudentInfo(studentId, request);
        return ResponseEntity.ok("학생 정보가 수정되었습니다.");
    }
}