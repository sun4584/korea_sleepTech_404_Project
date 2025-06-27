package com.example.back404.teamproject.controller.classroom;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classroom/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    public ResponseEntity<?> getAttendance() {
        return ResponseEntity.ok("출석 정보 조회");
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> recordAttendance(@RequestBody Object dto) {
        return ResponseEntity.ok("출석 체크");
    }
}