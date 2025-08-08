package com.high_school_course_registration.service;

import com.high_school_course_registration.dto.registration.request.EnrollmentRequestDto;
import com.high_school_course_registration.dto.registration.response.MyEnrollmentDto;
import java.util.List;

public interface EnrollmentService {

    MyEnrollmentDto createEnrollment(String username, EnrollmentRequestDto requestDto);
    void cancelEnrollment(String username, Long enrollmentId);
    List<MyEnrollmentDto> getMyEnrollments(String username);
}