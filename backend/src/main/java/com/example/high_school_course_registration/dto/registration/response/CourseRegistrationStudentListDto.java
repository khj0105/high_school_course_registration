package com.example.high_school_course_registration.dto.registration.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRegistrationStudentListDto {
    private String studentId;
    private String studentNumber;
    private String studentName;
    private int studentGrade;
    private int studentClass;
    private String studentEmail;
    private CourseRegistrationStatus status;
}