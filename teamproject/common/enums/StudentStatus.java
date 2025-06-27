package com.example.back404.teamproject.common.enums;

public enum StudentStatus {
    PENDING("승인 대기"),
    APPROVED("재학 중"),
    GRADUATED("졸업"),
    REJECTED("승인 거절");

    private final String description;

    StudentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}