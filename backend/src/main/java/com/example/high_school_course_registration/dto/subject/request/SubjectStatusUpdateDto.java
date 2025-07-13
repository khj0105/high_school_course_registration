package com.example.high_school_course_registration.dto.subject.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectStatusUpdateDto {
    @NotNull
    private SubjectStatus status;
}
