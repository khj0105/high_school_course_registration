package com.high_school_course_registration.service;

import com.high_school_course_registration.dto.course.request.CourseCreateRequestDto;
import com.high_school_course_registration.dto.course.request.CourseUpdateRequestDto;
import com.high_school_course_registration.dto.course.response.CourseDetailDto;
import com.high_school_course_registration.dto.course.response.CourseSimpleDto;

import java.util.List;

public interface CourseService {

    CourseDetailDto createCourse(CourseCreateRequestDto requestDto, String username);
    List<CourseSimpleDto> getAllCourses(String username);
    CourseDetailDto getCourseById(Long courseId, String username);
    CourseDetailDto updateCourse(Long courseId, CourseUpdateRequestDto requestDto, String username);
    void deleteCourse(Long courseId, String username);
}