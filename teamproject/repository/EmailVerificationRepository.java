package com.example.back404.teamproject.repository;

import com.example.back404.teamproject.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    Optional<EmailVerification> findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}