package com.example.high_school_course_registration.dto.피드백_auth.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherLoginResponseDto {
    private String teacherId;
    private String teacherName;
    private String teacherEmail;
    private String teacherSubject;
    private String token;
}