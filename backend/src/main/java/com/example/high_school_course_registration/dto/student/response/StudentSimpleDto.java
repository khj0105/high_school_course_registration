package com.example.high_school_course_registration.dto.student.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentSimpleDto {
    private Long id;
    private String studentNumber;
    private String studentName;
    private int studentGrade;
    private String classroomName;
    private String studentEmail;
}
