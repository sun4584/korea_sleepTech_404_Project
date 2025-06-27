package com.example.back404.teamproject.common.enums;

public enum TeacherStatus {
    PENDING("승인 대기"),
    APPROVED("승인 완료"),
    ON_LEAVE("휴직"),
    RETIRED("퇴직");

    private final String description;

    TeacherStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}