package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.SchoolCredentialIssuance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolCredentialIssuanceRepository extends JpaRepository<SchoolCredentialIssuance, Long> {
    // 학교 신청 ID로 발급 이력 조회
    Optional<SchoolCredentialIssuance> findBySchoolApplicationId(Long schoolApplicationId);
}