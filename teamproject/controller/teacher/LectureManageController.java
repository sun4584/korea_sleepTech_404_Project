package com.example.back404.teamproject.controller.teacher;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher/lectures")
@RequiredArgsConstructor
public class LectureManageController {

    @GetMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> getMyLectures() {
        return ResponseEntity.ok("내 강의 목록");
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> createLecture(@RequestBody Object dto) {
        return ResponseEntity.ok("강의 등록");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> updateLecture(@PathVariable Long id, @RequestBody Object dto) {
        return ResponseEntity.ok("강의 수정");
    }
}