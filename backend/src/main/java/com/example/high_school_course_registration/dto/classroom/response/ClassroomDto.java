package com.example.high_school_course_registration.dto.classroom.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClassroomDto {
    private Long id;
    private String classroomName;
    private String locationInfo;
}
