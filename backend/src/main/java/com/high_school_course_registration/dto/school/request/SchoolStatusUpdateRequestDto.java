package com.high_school_course_registration.dto.school.request;

import com.high_school_course_registration.common.enums.SchoolApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SchoolStatusUpdateRequestDto {
    @NotNull
    private SchoolApplicationStatus status;
}
