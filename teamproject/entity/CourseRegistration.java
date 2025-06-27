package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.CourseRegistrationApprovalStatus;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_registration")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CourseRegistration extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private Long registrationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    private Lecture lecture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private CourseRegistrationApprovalStatus status = CourseRegistrationApprovalStatus.PENDING;

    // 기존 코드 호환성을 위한 getter들
    public Long getId() {
        return this.registrationId;
    }

    public CourseRegistrationApprovalStatus getStatus() {
        return this.status;
    }

    public CourseRegistrationApprovalStatus getCourseRegistrationApprovalStatus() {
        return this.status;
    }

    // 상태 변경 메서드
    public void updateStatus(CourseRegistrationApprovalStatus newStatus) {
        this.status = newStatus;
    }
}