package com.example.high_school_course_registration.dto.lecture.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LectureListDto {
    private Long lectureId;
    private String subjectName;
    private String teacherName;
    private LectureDayOfWeek dayOfWeek;
    private int period;
    private int allowedGrade;
}