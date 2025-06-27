package com.example.back404.teamproject.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    STUDENT_NOT_FOUND("해당 학생이 존재하지 않습니다."),
    INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),
    LECTURE_NOT_FOUND("해당 강의가 존재하지 않습니다."),
    ALREADY_REGISTERED("이미 수강신청한 강의입니다."),
    LECTURE_FULL("강의 정원이 초과되었습니다."),
    REGISTRATION_NOT_FOUND("해당 수강신청 내역이 존재하지 않습니다.");

    private final String message;
}