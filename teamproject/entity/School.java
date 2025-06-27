package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.SchoolStatus;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "school")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class School extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private Long schoolId;

    @Column(nullable = false, unique = true)
    private Integer schoolCode;

    private String schoolName;
    private String schoolAddress;
    private String schoolContactNumber;
    private String schoolEmail;

    private String schoolAdminUsername;
    private String schoolAdminPassword;
    private String schoolAdminName;
    private String schoolAdminPhoneNumber;
    private String schoolAdminEmail;
    private LocalDate schoolAdminBirthDate;

    private String schoolPassword;

    private LocalDate applicationStartedDay;
    private LocalDate applicationLimitedDay;

    @Enumerated(EnumType.STRING)
    private SchoolStatus status;

    public void updatePassword(String newPassword) {
        this.schoolPassword = newPassword;
    }

    public void updateStatus(SchoolStatus status) {
        this.status = status;
    }
}