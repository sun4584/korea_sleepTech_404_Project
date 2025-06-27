package com.example.back404.teamproject.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserSignInResponseDto {
    private String token;
    private long expirationTime;
}