package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.StudentStatus;
import com.example.back404.teamproject.common.enums.SubjectAffiliation;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Student extends BaseTimeEntity {

    @Id
    @Column(name = "student_id")
    private String studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(name = "student_username", unique = true, nullable = false)
    private String studentUsername;

    @Column(name = "student_password", nullable = false)
    private String studentPassword;

    @Column(name = "student_number", unique = true, nullable = false)
    private String studentNumber;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "student_grade", nullable = false)
    private String studentGrade;

    @Column(name = "student_email", unique = true, nullable = false)
    private String studentEmail;

    @Column(name = "student_phone_number", nullable = false)
    private String studentPhoneNumber;

    @Column(name = "student_birth_date", nullable = false)
    private LocalDate studentBirthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_affiliation", nullable = false)
    private SubjectAffiliation studentAffiliation;

    @Enumerated(EnumType.STRING)
    @Column(name = "student_status", nullable = false)
    private StudentStatus studentStatus;

    @Column(name = "student_admission_year", nullable = false)
    private Year studentAdmissionYear;

    // 관계 매핑
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseRegistration> courseRegistrations;

    // 호환성을 위한 getter 메서드들 추가
    public String getUsername() {
        return this.studentUsername;
    }

    public String getName() {
        return this.studentName;
    }

    public String getEmail() {
        return this.studentEmail;
    }

    public String getPassword() {
        return this.studentPassword;
    }

    public String getPhoneNumber() {
        return this.studentPhoneNumber;
    }

    public LocalDate getBirthDate() {
        return this.studentBirthDate;
    }

    public Long getId() {
        return Long.parseLong(this.studentId);
    }

    // 업데이트 메서드들
    public void update(String studentName, String studentPhoneNumber, String studentEmail) {
        this.studentName = studentName;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentEmail = studentEmail;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public void setPassword(String password) {
        this.studentPassword = password;
    }

    public void setStatus(StudentStatus status) {
        this.studentStatus = status;
    }
}