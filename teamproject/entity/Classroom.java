package com.example.back404.teamproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "classroom")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Long id;

    @Column(name = "classroom_name", nullable = false)
    private String name;
}