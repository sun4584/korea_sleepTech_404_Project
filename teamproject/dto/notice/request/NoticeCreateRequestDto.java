package com.example.back404.teamproject.dto.notice.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeCreateRequestDto {
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotNull(message = "대상은 필수입니다.")
    private String targetAudience; // ALL, STUDENT, TEACHER

    @NotBlank(message = "시작일은 필수입니다.")
    private String startDate; // yyyy-MM-dd

    @NotBlank(message = "종료일은 필수입니다.")
    private String endDate; // yyyy-MM-dd
}
