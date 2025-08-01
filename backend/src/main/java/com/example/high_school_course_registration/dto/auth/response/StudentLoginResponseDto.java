package com.example.high_school_course_registration.dto.auth.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentLoginResponseDto {
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private int studentGrade;
    private String token;
}