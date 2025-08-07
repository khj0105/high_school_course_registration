package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.SchoolPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolPolicyRepository extends JpaRepository<SchoolPolicy, Long> {

    Optional<SchoolPolicy> findBySchool(School school);
}