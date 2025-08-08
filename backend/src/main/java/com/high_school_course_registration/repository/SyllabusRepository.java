package com.high_school_course_registration.repository;

import com.high_school_course_registration.entity.Course;
import com.high_school_course_registration.entity.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SyllabusRepository extends JpaRepository<Syllabus, Long> {

    Optional<Syllabus> findByCourse(Course course);
}
