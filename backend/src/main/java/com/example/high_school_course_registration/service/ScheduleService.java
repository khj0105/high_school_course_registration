package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.schedule.ScheduleDto;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> getMySchedule(String username);
}