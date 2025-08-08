package com.high_school_course_registration.repository;

import com.high_school_course_registration.common.enums.SchoolApplicationStatus;
import com.high_school_course_registration.entity.SchoolApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolApplicationRepository extends JpaRepository<SchoolApplication, Long> {

    List<SchoolApplication> findBySchoolApplicationStatus(SchoolApplicationStatus status);

    boolean existsBySchoolEmail(String schoolEmail);
}