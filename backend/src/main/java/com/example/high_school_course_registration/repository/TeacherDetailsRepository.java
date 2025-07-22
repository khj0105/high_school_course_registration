package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.TeacherDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherDetailsRepository extends JpaRepository<TeacherDetails, Long> {
}