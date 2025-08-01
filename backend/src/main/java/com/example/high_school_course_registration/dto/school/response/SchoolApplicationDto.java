package com.example.high_school_course_registration.dto.school.response;

import com.example.high_school_course_registration.common.enums.SchoolApplicationStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SchoolApplicationDto {
    private Long id;
    private String schoolName;
    private String schoolAdminName;
    private SchoolApplicationStatus status;
    private LocalDateTime createdAt;
}
