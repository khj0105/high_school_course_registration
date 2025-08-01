package com.example.high_school_course_registration.dto.syllabus.request;

import lombok.Getter;

@Getter
public class SyllabusUpdateRequestDto {
    private String content;
    private String learningObjectives;
    private String gradingPolicy;
}
