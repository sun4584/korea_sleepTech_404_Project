package com.example.back404.teamproject.common.enums;

import lombok.Getter;

@Getter
public enum SubjectStatus {
    PENDING("승인 대기"),
    APPROVED("승인 완료"),
    UPDATED("과목 수정"),
    REJECTED("승인 거절"),
    DELETED("과목 삭제");

    private final String description;

    SubjectStatus(String description) {
        this.description = description;
    }
}