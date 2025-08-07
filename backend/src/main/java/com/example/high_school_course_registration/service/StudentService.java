package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.common.enums.StudentStatus;
import com.example.high_school_course_registration.dto.student.request.StudentUpdateRequestDto;
import com.example.high_school_course_registration.dto.student.response.StudentDetailDto;
import com.example.high_school_course_registration.dto.student.response.StudentSimpleDto;

import java.util.List;

public interface StudentService {

    StudentDetailDto getStudentProfile(String username);
    StudentDetailDto updateStudentProfile(String username, StudentUpdateRequestDto requestDto);


    StudentDetailDto getStudentById(Long studentId);
    List<StudentSimpleDto> getAllStudents();
    List<StudentSimpleDto> getPendingStudents(String adminUsername);
    StudentDetailDto updateStudentStatus(Long studentId, StudentStatus status, String adminUsername);
}