package com.example.high_school_course_registration.dto.lecture.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LectureUpdateRequestDto {
    @NotBlank
    private String teacherId;

    @NotNull
    private LectureDayOfWeek dayOfWeek;

    @NotNull
    @Min(1) @Max(8)
    private int period;

    @NotNull
    @Min(0) @Max(30)
    private int maxEnrollment;
}