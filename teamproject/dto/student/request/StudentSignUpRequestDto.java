package com.example.back404.teamproject.dto.student.request;

import com.example.back404.teamproject.common.enums.StudentStatus;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class StudentSignUpRequestDto {

    @NotBlank(message = "사용자명은 필수입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "학번은 필수입니다.")
    private String studentNumber;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Min(value = 1, message = "학년은 1 이상이어야 합니다.")
    @Max(value = 3, message = "학년은 3 이하여야 합니다.")
    private int grade;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "전화번호는 필수입니다.")
    private String phoneNumber;

    @NotNull(message = "생년월일은 필수입니다.")
    private String birthDate;

    @NotNull(message = "계열은 필수입니다.")
    private SubjectAffiliation affiliation;

    @Min(value = 2000, message = "입학년도는 2000년 이후여야 합니다.")
    private int admissionYear;

    @Pattern(regexp = "male|female", message = "성별은 male 또는 female이어야 합니다.")
    private String gender;

    @NotNull(message = "상태는 필수입니다.")
    private StudentStatus status;
}