package com.example.back404.teamproject.dto.subject.response;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.enums.SubjectStatus;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SubjectGetResponseDto {
    private String subjectId;
    private Long schoolId;
    private String subjectName;
    private String subjectGrade;
    private String subjectSemester;
    private SubjectAffiliation subjectAffiliation;
    private SubjectStatus subjectStatus;
    private Integer subjectMaxEnrollment;
}
