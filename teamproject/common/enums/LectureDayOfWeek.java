package com.example.back404.teamproject.common.enums;

public enum LectureDayOfWeek {
    MONDAY("월요일"),
    TUESDAY("화요일"),
    WEDNESDAY("수요일"),
    THURSDAY("목요일"),
    FRIDAY("금요일");

    private final String description;

    LectureDayOfWeek(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}