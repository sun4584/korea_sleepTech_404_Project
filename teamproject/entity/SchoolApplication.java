package com.example.back404.teamproject.entity;

import com.example.back404.teamproject.common.enums.SchoolStatus;
import com.example.back404.teamproject.entity.datatime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school_application")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SchoolApplication extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_application_id")
    private Long schoolApplicationId;

    @Column(nullable = false)
    private String schoolName;

    @Column(nullable = false)
    private String schoolAddress;

    @Column(nullable = false)
    private String schoolContactNumber;

    @Column(nullable = false, unique = true)
    private String schoolAdminUsername;

    @Column(nullable = false)
    private String schoolAdminPassword;

    @Column(nullable = false)
    private String schoolAdminName;

    @Column(nullable = false)
    private String schoolAdminBirthDate;

    @Column(nullable = false)
    private String schoolAdminPhoneNumber;

    @Column(nullable = false)
    private String schoolAdminEmail;

    @Column(nullable = false)
    private String schoolEmail;

    @Column(nullable = false)
    private String applicationStartedDay;

    @Column(nullable = false)
    private String applicationLimitedDay;

    @Column(nullable = false)
    private Integer schoolCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SchoolStatus status;

    public void updateStatus(SchoolStatus newStatus) {
        this.status = newStatus;
    }
}