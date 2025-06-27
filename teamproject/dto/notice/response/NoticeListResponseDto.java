package com.example.back404.teamproject.dto.notice.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeListResponseDto {
    private Long noticeId;
    private String title;
    private String targetAudience;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
}