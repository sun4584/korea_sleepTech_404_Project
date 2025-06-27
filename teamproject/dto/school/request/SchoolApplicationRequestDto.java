package com.example.back404.teamproject.dto.school.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolApplicationRequestDto {

    @NotBlank(message = "학교명은 필수입니다.")
    private String schoolName;

    @NotBlank(message = "학교 주소는 필수입니다.")
    private String schoolAddress;

    @NotBlank(message = "학교 연락처는 필수입니다.")
    private String schoolContactNumber;

    @NotBlank(message = "관리자 사용자명은 필수입니다.")
    private String schoolAdminUsername;

    @NotBlank(message = "관리자 비밀번호는 필수입니다.")
    private String schoolAdminPassword;

    @NotBlank(message = "관리자 이름은 필수입니다.")
    private String schoolAdminName;

    @NotBlank(message = "관리자 생년월일은 필수입니다.")
    private String schoolAdminBirthDate;

    @NotBlank(message = "관리자 전화번호는 필수입니다.")
    private String schoolAdminPhoneNumber;

    @NotBlank(message = "관리자 이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String schoolAdminEmail;

    @NotBlank(message = "학교 이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String schoolEmail;

    @NotBlank(message = "신청 시작일은 필수입니다.")
    private String applicationStartedDay;

    @NotBlank(message = "신청 마감일은 필수입니다.")
    private String applicationLimitedDay;

    @NotNull(message = "학교 코드는 필수입니다.")
    private Integer schoolCode;
}