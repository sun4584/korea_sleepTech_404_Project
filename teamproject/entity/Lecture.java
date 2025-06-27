package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.LectureDayOfWeek;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lecture")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Lecture extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long lectureId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_day_of_week", nullable = false)
    private LectureDayOfWeek lectureDayOfWeek;

    @Column(name = "lecture_period", nullable = false)
    private Integer lecturePeriod;

    @Column(name = "lecture_allowed_grade", nullable = false)
    private String lectureAllowedGrade;

    @Column(name = "lecture_max_enrollment", nullable = false)
    private Integer lectureMaxEnrollment;

    // 관계 매핑
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseRegistration> courseRegistrations;

    // 호환성을 위한 getter 메서드들 추가
    public LectureDayOfWeek getDayOfWeek() {
        return this.lectureDayOfWeek;
    }

    public Integer getPeriod() {
        return this.lecturePeriod;
    }

    public Integer getMaxEnrollment() {
        return this.lectureMaxEnrollment;
    }

    public String getAllowedGrade() {
        return this.lectureAllowedGrade;
    }

    public Classroom getClassroom() {
        return this.classroom;
    }

    // 업데이트 메서드
    public void updateInfo(Teacher teacher, LectureDayOfWeek lectureDayOfWeek, 
                          Integer lecturePeriod, Integer lectureMaxEnrollment) {
        this.teacher = teacher;
        this.lectureDayOfWeek = lectureDayOfWeek;
        this.lecturePeriod = lecturePeriod;
        this.lectureMaxEnrollment = lectureMaxEnrollment;
    }
}
