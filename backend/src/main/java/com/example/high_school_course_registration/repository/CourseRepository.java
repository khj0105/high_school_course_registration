package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.Course;
import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // 특정 학교, 년도, 학기에 개설된 모든 강의 조회
    List<Course> findBySchoolAndYearAndSemester(School school, int year, Long semester);

    // 특정 교사가 특정 년도, 학기에 담당하는 모든 강의 조회
    List<Course> findByTeacherAndYearAndSemester(User teacher, int year, Long semester);

    // 과목명으로 가으이 검색(학생용)
    List<Course> findBySubjectNameContaining(School school, int year, Long semester, String subjectName);
}