package com.example.back404.teamproject.controller.student;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/me")
@RequiredArgsConstructor
public class StudentMyPageController {

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getMyInfo() {
        return ResponseEntity.ok("내 정보 조회");
    }

    @PutMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> updateMyInfo(@RequestBody Object dto) {
        return ResponseEntity.ok("내 정보 수정");
    }
}