package com.high_school_course_registration.dto.teacher.request;

import com.high_school_course_registration.common.enums.TeacherStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TeacherStatusUpdateRequestDto {
    @NotNull
    private TeacherStatus teacherStatus;
}
