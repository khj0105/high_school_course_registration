package com.example.high_school_course_registration.dto.teacher.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TeacherCreateRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String subject;
}
