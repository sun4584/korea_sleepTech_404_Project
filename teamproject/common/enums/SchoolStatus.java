package com.example.back404.teamproject.common.enums;

import lombok.Getter;

@Getter
public enum SchoolStatus {
    PENDING("승인 대기"),
    APPROVED("승인 완료"),
    REJECTED("승인 반려");

    private final String description;

    SchoolStatus(String description) {
        this.description = description;
    }
}