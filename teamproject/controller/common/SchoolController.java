package com.example.back404.teamproject.controller.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SchoolController {

    // GET /api/v1/schools
    @GetMapping("/api/v1/schools")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllSchools() {
        return ResponseEntity.ok("학교 목록");
    }
}