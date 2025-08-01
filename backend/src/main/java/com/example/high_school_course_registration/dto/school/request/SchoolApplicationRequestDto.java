package com.example.high_school_course_registration.dto.school.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SchoolApplicationRequestDto {
    @NotBlank
    private String schoolName;
    @NotBlank
    private String schoolAddress;
    @NotBlank
    private String schoolEmail;
    @NotBlank
    private String schoolContactNumber;
    @NotBlank
    private String schoolAdminName;
    @NotBlank
    private String schoolAdminPhoneNumber;
}