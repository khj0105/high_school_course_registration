package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.common.enums.TeacherStatus;
import com.example.high_school_course_registration.dto.teacher.request.TeacherCreateRequestDto;
import com.example.high_school_course_registration.dto.teacher.response.TeacherDetailDto;
import com.example.high_school_course_registration.dto.teacher.response.TeacherSimpleDto;

import java.util.List;

public interface TeacherService {

    TeacherDetailDto getTeacherProfile(String username);

    TeacherDetailDto createTeacher(TeacherCreateRequestDto requestDto, String adminUsername);
    TeacherDetailDto updateTeacherStatus(Long teacherId, TeacherStatus status, String adminUsername);
    List<TeacherSimpleDto> getAllTeachers(String adminUsername);
    List<TeacherSimpleDto> getPendingTeachers(String adminUsername);
}