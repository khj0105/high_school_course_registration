package com.example.high_school_course_registration.service;

public interface EmailSender {
    void send(String to, String subject, String text);
}
