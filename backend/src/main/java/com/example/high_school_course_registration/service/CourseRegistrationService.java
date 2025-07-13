package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.registration.response.EnrolledStudentListDto;
import com.example.high_school_course_registration.dto.registration.response.LectureRegistrationSummaryDto;

import java.util.List;

public interface CourseRegistrationService {
    ResponseDto<LectureRegistrationSummaryDto> getRegisteredStudentsByLectureId(String username, Long lectureId);
    ResponseDto<List<EnrolledStudentListDto>> getEnrolledStudentsByLectureId(String username, Long lectureId);
}
