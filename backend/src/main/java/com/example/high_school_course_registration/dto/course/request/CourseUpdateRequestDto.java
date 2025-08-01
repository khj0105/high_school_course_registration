package com.example.high_school_course_registration.dto.course.request;

import com.example.high_school_course_registration.common.enums.DayOfWeek;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CourseUpdateRequestDto {
    @NotBlank
    private String teacherId;

    @NotNull
    private DayOfWeek dayOfWeek;

    @NotNull
    @Min(1) @Max(8)
    private int period;

    @NotNull
    @Min(0) @Max(30)
    private int maxEnrollment;
}