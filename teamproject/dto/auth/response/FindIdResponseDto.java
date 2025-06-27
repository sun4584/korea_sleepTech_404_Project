package com.example.back404.teamproject.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindIdResponseDto {
    private String message;
    private String username;
}