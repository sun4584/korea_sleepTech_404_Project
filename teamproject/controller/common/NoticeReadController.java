package com.example.back404.teamproject.controller.common;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common/notices")
@RequiredArgsConstructor
public class NoticeReadController {

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllNotices() {
        return ResponseEntity.ok("공지사항 목록 조회");
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getNoticeDetail(@PathVariable Long id) {
        return ResponseEntity.ok("공지사항 상세 조회");
    }
}