package com.example.high_school_course_registration.service;

public interface EmailAuthService {
    void sendVerificationLink(String email);
    boolean verifyToken(String token);
}
