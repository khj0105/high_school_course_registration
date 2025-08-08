package com.high_school_course_registration.dto.student.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class StudentSignUpRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String studentNumber;

    @NotBlank
    private String studentName;

    @Min(1) @Max(3)
    private int studentGrade;

    @Email
    @NotBlank
    private String studentEmail;

    @NotBlank
    private String studentPhoneNumber;

    @NotNull
    private LocalDate studentBirthDate;

    @Min(2023)
    private int studentAdmissionYear;

    @NotNull
    private Long classroomId;
}
