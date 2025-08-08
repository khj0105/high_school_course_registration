package com.high_school_course_registration.service;

import com.high_school_course_registration.dto.school.request.RegistrationPeriodRequestDto;

public interface SchoolManageService {

    void setRegistrationPeriod(String adminUsername, RegistrationPeriodRequestDto requestDto);
}
