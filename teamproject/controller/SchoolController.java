package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.school.request.SchoolUpdateRequestDto;
import com.example.back404.teamproject.service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/school")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSchoolInfo(@PathVariable Long id) {
        return ResponseEntity.ok(schoolService.getSchoolInfo(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchoolInfo(@PathVariable Long id,
                                              @RequestBody @Valid SchoolUpdateRequestDto dto) {
        return ResponseEntity.ok(schoolService.updateSchoolInfo(id, dto));
    }
}