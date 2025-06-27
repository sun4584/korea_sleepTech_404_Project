package com.example.back404.teamproject.service;

public interface EmailSender {
    void send(String to, String subject, String text);
}