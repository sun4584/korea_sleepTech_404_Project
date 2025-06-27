package com.example.back404.teamproject.controller.notice;

import com.example.back404.teamproject.common.ApiMappingPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    // 공지사항 열람 (공통 - 모든 인증된 사용자)
    @GetMapping("/common/notices")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAllNotices(@RequestParam(required = false) String category) {
        return ResponseEntity.ok("공지사항 목록 조회");
    }

    @GetMapping("/common/notices/{noticeId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getNoticeDetail(@PathVariable Long noticeId) {
        return ResponseEntity.ok("공지사항 상세 조회");
    }

    // 공지사항 관리 (관리자 전용)
    @PostMapping("/admin/notices")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createNotice(@RequestBody Object dto) {
        return ResponseEntity.ok("공지사항 등록");
    }

    @PutMapping("/admin/notices/{noticeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateNotice(@PathVariable Long noticeId, @RequestBody Object dto) {
        return ResponseEntity.ok("공지사항 수정");
    }

    @DeleteMapping("/admin/notices/{noticeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteNotice(@PathVariable Long noticeId) {
        return ResponseEntity.ok("공지사항 삭제");
    }
}