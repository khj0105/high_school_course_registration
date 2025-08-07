package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.registration.response.EnrolledStudentDto;
import com.example.high_school_course_registration.dto.registration.response.CourseEnrollmentSummaryDto;

import java.util.List;

public interface CourseRegistrationService {
    ResponseDto<CourseEnrollmentSummaryDto> getRegisteredStudentsByLectureId(String username, Long lectureId);
    ResponseDto<List<EnrolledStudentDto>> getEnrolledStudentsByLectureId(String username, Long lectureId);
}
