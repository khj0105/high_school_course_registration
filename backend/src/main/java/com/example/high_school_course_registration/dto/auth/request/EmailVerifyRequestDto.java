package com.example.high_school_course_registration.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailVerifyRequestDto {
    @NotBlank
    private String email;

    @NotBlank
    private String code;
}
