package com.example.back404.teamproject.controller.student;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student/course-apply")
@RequiredArgsConstructor
public class CourseApplyController {

    @PostMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> applyCourse(@RequestBody Object dto) {
        return ResponseEntity.ok("수강 신청");
    }

    @GetMapping
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getMyCourseApplications() {
        return ResponseEntity.ok("내 수강신청 목록");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> cancelCourseApplication(@PathVariable Long id) {
        return ResponseEntity.ok("수강신청 취소");
    }
}