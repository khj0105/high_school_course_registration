package com.example.high_school_course_registration.dto.teacher.response;

import com.example.high_school_course_registration.common.enums.TeacherStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherDetailDto {
    private Long id;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private String subject;
    private TeacherStatus teacherStatus;
}
