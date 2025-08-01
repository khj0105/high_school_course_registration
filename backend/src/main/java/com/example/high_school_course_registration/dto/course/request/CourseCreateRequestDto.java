package com.example.high_school_course_registration.dto.course.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CourseCreateRequestDto {
    @NotNull(message = "과목 ID는 필수입니다.")
    private Long subjectId;

    @NotNull(message = "담당 교사 ID는 필수입니다.")
    private Long teacherId;

    @NotNull(message = "연도는 필수입니다.")
    @Min(2023)
    private int year;

    @NotNull(message = "학기는 필수입니다.")
    @Min(1) @Max(2)
    private int semester;

    @NotNull(message = "최대 수강 인원은 필수입니다.")
    @Min(1)
    private int maxEnrollment;
}
