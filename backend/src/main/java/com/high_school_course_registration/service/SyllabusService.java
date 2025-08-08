package com.high_school_course_registration.service;

import com.high_school_course_registration.dto.syllabus.request.SyllabusUpdateRequestDto;
import com.high_school_course_registration.dto.syllabus.response.SyllabusResponseDto;

public interface SyllabusService {

    SyllabusResponseDto getSyllabus(Long courseId, String username);
    SyllabusResponseDto updateSyllabus(Long courseId, SyllabusUpdateRequestDto requestDto, String username);
}