package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.school.response.SchoolApplicationDto;

import java.util.List;

public interface SuperAdminService {

    List<SchoolApplicationDto> getPendingApplications();
    void approveSchoolApplication(Long applicationId);
    void rejectSchoolApplication(Long applicationId);
}