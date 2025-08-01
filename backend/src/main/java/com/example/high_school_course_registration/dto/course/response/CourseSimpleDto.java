package com.example.high_school_course_registration.dto.course.response;

import com.example.high_school_course_registration.common.enums.DayOfWeek;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseSimpleDto {
    private Long id;
    private String subjectName;
    private String teacherName;
    private DayOfWeek dayOfWeek;
    private int period;
    private int grade;
}