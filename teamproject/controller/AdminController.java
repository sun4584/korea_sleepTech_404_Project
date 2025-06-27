package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.dto.school.request.SchoolInfoUpdateRequestDto;
import com.example.back404.teamproject.dto.school.request.ChangePasswordRequestDto;
import com.example.back404.teamproject.service.SchoolAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final SchoolAdminService schoolAdminService;

    @GetMapping("/info")
    public ResponseEntity<?> getAdminInfo() {
        return ResponseEntity.ok(schoolAdminService.getAdminInfo());
    }

    @PutMapping("/info")
    public ResponseEntity<?> updateAdminInfo(@RequestBody @Valid SchoolInfoUpdateRequestDto dto) {
        return ResponseEntity.ok(schoolAdminService.updateAdminInfo(dto));
    }

    @PutMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequestDto dto) {
        return ResponseEntity.ok(schoolAdminService.changePassword(dto));
    }
}