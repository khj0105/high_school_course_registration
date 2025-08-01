package com.example.high_school_course_registration.dto.classroom.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ClassroomRequestDto {
    @NotBlank
    private String classroomName;
    private String locationInfo;
}
