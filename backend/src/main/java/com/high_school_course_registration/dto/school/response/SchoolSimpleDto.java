package com.high_school_course_registration.dto.school.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolSimpleDto {
    private Long id;
    private String schoolCode;
    private String schoolName;
}