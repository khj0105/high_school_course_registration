package com.example.high_school_course_registration.dto.course.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseDetailResponseDto {
    private Long id;
    private String subjectName;
    private String subjectDescription;
    private String teacherName;
    private String dayOfWeek;
    private int period;
    private int maxStudents;
    private int currentStudents;
    private String classroom;
}
