package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.SchoolCredentialIssuance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolCredentialIssuanceRepository extends JpaRepository<SchoolCredentialIssuance, Long> {

    Optional<SchoolCredentialIssuance> findBySchoolApplicationId(Long schoolApplicationId);
}