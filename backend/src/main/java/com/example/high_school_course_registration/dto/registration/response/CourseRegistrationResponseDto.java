package com.example.high_school_course_registration.dto.registration.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CourseRegistrationResponseDto {
    private Long registrationId;
    private Long lectureId;
    private String lectureName;
    private String subjectName;
    private String teacherName;
    private LectureDayOfWeek dayOfWeek;
    private int period;
}
