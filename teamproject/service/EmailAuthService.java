package com.example.back404.teamproject.service;

public interface EmailAuthService {
    void sendVerificationLink(String email);
    boolean verifyToken(String token);
}