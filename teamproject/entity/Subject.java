package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.common.enums.SubjectStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subject")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Subject {

    @Id
    @Column(name = "subject_id")
    private String subjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "subject_grade", nullable = false)
    private String subjectGrade;

    @Column(name = "subject_semester", nullable = false)
    private String subjectSemester;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_affiliation", nullable = false)
    private SubjectAffiliation subjectAffiliation;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject_status", nullable = false)
    private SubjectStatus subjectStatus;

    @Column(name = "subject_max_enrollment", nullable = false)
    private Integer subjectMaxEnrollment;

    @Column(name = "subject_description")
    private String subjectDescription;

    // 호환성을 위한 getter 메서드들 추가
    public String getDescription() {
        return this.subjectDescription;
    }

    public String getSubjectDescription() {
        return this.subjectDescription;
    }

    // 업데이트 메서드들
    public void updateStatus(SubjectStatus newStatus) {
        this.subjectStatus = newStatus;
    }

    public void updateInfo(String subjectName, String subjectGrade, String subjectSemester, 
                          SubjectAffiliation subjectAffiliation, Integer subjectMaxEnrollment) {
        this.subjectName = subjectName;
        this.subjectGrade = subjectGrade;
        this.subjectSemester = subjectSemester;
        this.subjectAffiliation = subjectAffiliation;
        this.subjectMaxEnrollment = subjectMaxEnrollment;
    }
}
