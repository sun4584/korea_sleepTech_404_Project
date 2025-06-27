package com.example.back404.teamproject.controller.registration;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CourseRegistrationController {

    // 수강 신청
    @PostMapping("/student/course-apply")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> applyCourse(@RequestBody Object dto) {
        return ResponseEntity.ok("수강 신청");
    }

    @GetMapping("/student/course-apply")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> getMyCourseApplications() {
        return ResponseEntity.ok("내 수강신청 목록");
    }

    @DeleteMapping("/student/course-apply/{registrationId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<?> cancelCourseApplication(@PathVariable Long registrationId) {
        return ResponseEntity.ok("수강신청 취소");
    }
}