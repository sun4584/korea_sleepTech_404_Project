package com.example.back404.teamproject.controller.student;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentManageController {

    // 학생 조회 (관리자/교사용)
    @GetMapping("/manage/students")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok("학생 목록 조회");
    }

    @GetMapping("/manage/students/{studentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<?> getStudentDetail(@PathVariable String studentId) {
        return ResponseEntity.ok("학생 상세 조회");
    }

    // 본인 정보 조회 (학생용)
    @GetMapping("/student/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getMyInfo() {
        return ResponseEntity.ok("내 정보 조회");
    }

    @PutMapping("/student/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> updateMyInfo(@RequestBody Object dto) {
        return ResponseEntity.ok("내 정보 수정");
    }
}