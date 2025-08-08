package com.high_school_course_registration.dto.auth.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherLoginResponseDto {
    private Long teacherId;
    private String teacherName;
    private String teacherEmail;
    private String teacherSubject;
    private String token;
}