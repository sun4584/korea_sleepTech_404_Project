package com.example.back404.teamproject.dto.notice.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeDetailResponseDto {
    private Long noticeId;
    private String title;
    private String content;
    private String targetAudience;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
