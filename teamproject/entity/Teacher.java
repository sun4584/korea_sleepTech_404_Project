package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.TeacherStatus;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "teacher")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Teacher extends BaseTimeEntity {

    @Id
    @Column(name = "teacher_id")
    private String teacherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "teacher_username", unique = true, nullable = false)
    private String teacherUsername;

    @Column(name = "teacher_password", nullable = false)
    private String teacherPassword;

    @Column(name = "teacher_name", nullable = false)
    private String teacherName;

    @Column(name = "teacher_email", unique = true, nullable = false)
    private String teacherEmail;

    @Column(name = "teacher_phone_number", nullable = false)
    private String teacherPhoneNumber;

    @Column(name = "teacher_subject", nullable = false)
    private String teacherSubject;

    @Column(name = "teacher_birth_date", nullable = false)
    private LocalDate teacherBirthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "teacher_status", nullable = false)
    private TeacherStatus teacherStatus;

    // 호환성을 위한 getter 메서드들 추가
    public String getUsername() {
        return this.teacherUsername;
    }

    public String getName() {
        return this.teacherName;
    }

    public String getEmail() {
        return this.teacherEmail;
    }

    public String getPassword() {
        return this.teacherPassword;
    }

    public TeacherStatus getStatus() {
        return this.teacherStatus;
    }

    // 업데이트 메서드들
    public void update(String teacherName, String teacherEmail, String teacherPhoneNumber, String teacherSubject) {
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherPhoneNumber = teacherPhoneNumber;
        this.teacherSubject = teacherSubject;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public void setTeacherStatus(TeacherStatus teacherStatus) {
        this.teacherStatus = teacherStatus;
    }

    public void setPassword(String password) {
        this.teacherPassword = password;
    }

    public void setStatus(TeacherStatus status) {
        this.teacherStatus = status;
    }
}