package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.registration.response.CourseEnrollmentSummaryDto;

public interface CourseRegistrationService {

    CourseEnrollmentSummaryDto getEnrolledStudents(Long courseId, String username);
}