package com.example.back404.teamproject.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolListDto {
    private Long schoolId;
    private String schoolCode;
    private String schoolName;
}