package com.example.back404.teamproject.dto.notice.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeUpdateRequestDto {
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    private String targetAudience; // ALL, STUDENT, TEACHER

    private String startDate; // yyyy-MM-dd

    private String endDate; // yyyy-MM-dd
}
