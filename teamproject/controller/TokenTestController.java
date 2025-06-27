package com.example.back404.teamproject.controller;

import com.example.back404.teamproject.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TokenTestController {

    private final JwtProvider jwtProvider;

    @GetMapping("/token")
    public ResponseEntity<?> generateTestToken(@RequestParam String email, @RequestParam String role) {
        String token = jwtProvider.generateToken(email, role);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("expirationTime", jwtProvider.getExpiration());
        return ResponseEntity.ok(response);
    }
}