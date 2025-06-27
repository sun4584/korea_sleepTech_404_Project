package com.example.back404.teamproject.controller.student;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/lectures")
@RequiredArgsConstructor
public class LectureReadController {

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getAllLectures() {
        return ResponseEntity.ok("강의 목록 조회");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getLectureDetail(@PathVariable Long id) {
        return ResponseEntity.ok("강의 상세 조회");
    }
}
