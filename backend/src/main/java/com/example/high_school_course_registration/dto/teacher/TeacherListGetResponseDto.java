package com.example.high_school_course_registration.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherListGetResponseDto {
    private String teacherUsername;
    private String teacherName;
    private String teacherEmail;
    private String teacherPhoneNumber;
    private String teacherSubject;
    private TeacherStatus teacherStatus;
}
