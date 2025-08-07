package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.classroom.request.ClassroomRequestDto;
import com.example.high_school_course_registration.dto.classroom.response.ClassroomDto;

import java.util.List;

public interface ClassroomService {

    ClassroomDto createClassroom(ClassroomRequestDto requestDto, String schoolCode);
    List<ClassroomDto> getAllClassrooms(String schoolCode);
    ClassroomDto updateClassroom(Long classroomId, ClassroomRequestDto requestDto, String schoolCode);
    void deleteClassroom(Long classroomId, String schoolCode);
}