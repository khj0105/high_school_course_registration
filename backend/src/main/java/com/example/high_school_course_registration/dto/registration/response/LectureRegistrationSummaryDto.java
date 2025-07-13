package com.example.high_school_course_registration.dto.registration.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureRegistrationSummaryDto {
    private Long lectureId;
    private String subjectName;
    private String teacherName;
    private Integer maxEnrollment;
    private int currentRegisteredCount;
    private List<CourseRegistrationStudentListDto> registeredStudents;
}