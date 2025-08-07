package com.example.high_school_course_registration.service;

public interface EmailAuthService {

    void sendVerificationEmail(String email);
    void verifyEmailCode(String email, String code);
}
