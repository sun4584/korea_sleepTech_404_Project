package com.example.back404.teamproject.common.enums;

public enum CourseRegistrationApprovalStatus {
    PENDING("승인 대기"),
    APPROVED("승인 완료"),
    REJECTED("승인 거절");

    private final String description;

    CourseRegistrationApprovalStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
