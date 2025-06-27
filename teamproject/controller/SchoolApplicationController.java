package com.example.back404.teamproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/school-applications")
@RequiredArgsConstructor
public class SchoolApplicationController {

    // POST /api/v1/school-applications
    @PostMapping
    public ResponseEntity<?> createSchoolApplication(@RequestBody Object dto) {
        return ResponseEntity.ok("학교 신청 완료");
    }

    // GET /api/v1/school-applications?status=pending
    @GetMapping
    public ResponseEntity<?> getSchoolApplications(@RequestParam(required = false) String status) {
        return ResponseEntity.ok("학교 신청 목록");
    }
}