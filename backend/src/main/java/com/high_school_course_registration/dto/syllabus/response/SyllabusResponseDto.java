package com.high_school_course_registration.dto.syllabus.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SyllabusResponseDto {
    private Long syllabusId;
    private Long courseId;
    private String content;
    private String learningObjectives;
    private String gradingPolicy;
}
