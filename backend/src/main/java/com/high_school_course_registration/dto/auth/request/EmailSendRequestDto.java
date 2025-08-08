package com.high_school_course_registration.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailSendRequestDto {
    @NotBlank
    @Email
    private String email;
}
