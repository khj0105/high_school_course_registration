package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.TeacherDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherDetailsRepository extends JpaRepository<TeacherDetails, Long> {

    List<TeacherDetails> findByUserSchool(School school);
}