package com.example.back404.teamproject.controller.lecture;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LectureController {

    // 강의 등록/관리 (교사용)
    @GetMapping("/teacher/lectures")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> getMyLectures() {
        return ResponseEntity.ok("내 강의 목록");
    }

    @PostMapping("/teacher/lectures")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> createLecture(@RequestBody Object dto) {
        return ResponseEntity.ok("강의 등록");
    }

    @PutMapping("/teacher/lectures/{lectureId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> updateLecture(@PathVariable Long lectureId, @RequestBody Object dto) {
        return ResponseEntity.ok("강의 수정");
    }

    // 강의 열람 (학생용)
    @GetMapping("/student/lectures")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getAllLectures() {
        return ResponseEntity.ok("강의 목록 조회");
    }

    @GetMapping("/student/lectures/{lectureId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getLectureDetail(@PathVariable Long lectureId) {
        return ResponseEntity.ok("강의 상세 조회");
    }
}