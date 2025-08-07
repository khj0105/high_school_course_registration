package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.school.request.RegistrationPeriodRequestDto;

public interface SchoolManageService {

    void setRegistrationPeriod(String adminUsername, RegistrationPeriodRequestDto requestDto);
}
