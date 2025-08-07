package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.school.response.SchoolSimpleDto;

import java.util.List;

public interface SchoolService {

    List<SchoolSimpleDto> getAllSchools();
}