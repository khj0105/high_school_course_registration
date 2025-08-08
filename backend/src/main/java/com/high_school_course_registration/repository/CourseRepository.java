package com.high_school_course_registration.repository;

import com.high_school_course_registration.entity.Course;
import com.high_school_course_registration.entity.School;
import com.high_school_course_registration.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findBySchoolAndYearAndSemester(School school, int year, Long semester);

    List<Course> findByTeacherAndYearAndSemester(User teacher, int year, Long semester);

    Page<Course> findBySchoolAndYearAndSemesterAndSubjectSubjectNameContaining(
            School school, int year, Long semester, String subjectName, Pageable pageable);

    List<Course> findBySchool(School school);
}