package com.example.high_school_course_registration.dto.auth.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SchoolLoginResponseDto {
    private String schoolCode;
    private Long schoolId;
    private String schoolName;
    private String schoolAdminName;
    private String token;
}
