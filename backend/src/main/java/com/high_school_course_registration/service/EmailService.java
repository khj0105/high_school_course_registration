package com.high_school_course_registration.service;

public interface EmailService {

    void sendSchoolCredentials(String toEmail, String schoolCode, String temporaryPassword);
}