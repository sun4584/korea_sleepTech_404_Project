package com.example.back404.teamproject.dto.subject.request;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectCreateRequestDto {
    @NotBlank(message = "과목 ID는 필수입니다.")
    private String subjectId;

    @NotBlank(message = "과목명은 필수입니다.")
    private String subjectName;

    @NotBlank(message = "학년은 필수입니다.")
    private String grade;

    @NotBlank(message = "학기는 필수입니다.")
    private String semester;

    @NotNull(message = "계열은 필수입니다.")
    private SubjectAffiliation affiliation;

    @NotNull(message = "최대 수강 인원은 필수입니다.")
    @Min(value = 1, message = "최대 수강 인원은 1명 이상이어야 합니다.")
    private Integer maxEnrollment;

    private String description;
}