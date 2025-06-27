package com.example.back404.teamproject.dto.subject.response;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectListGetResponseDto {
    private String subjectId;
    private String subjectName;
    private String subjectGrade;
    private String subjectSemester;
    private SubjectAffiliation subjectAffiliation;
}