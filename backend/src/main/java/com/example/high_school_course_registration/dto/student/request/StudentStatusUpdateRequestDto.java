package com.example.high_school_course_registration.dto.student.request;

import com.example.high_school_course_registration.common.enums.StudentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StudentStatusUpdateRequestDto {
    @NotNull
    private StudentStatus status;
}