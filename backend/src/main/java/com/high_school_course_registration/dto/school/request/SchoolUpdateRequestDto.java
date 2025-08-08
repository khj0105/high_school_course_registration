package com.high_school_course_registration.dto.school.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SchoolUpdateRequestDto {
    @NotBlank
    private String schoolName;
    @NotBlank
    private String schoolAddress;
    @NotBlank
    private String schoolContactNumber;
}