package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.common.enums.SchoolApplicationStatus;
import com.example.high_school_course_registration.entity.SchoolApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolApplicationRepository extends JpaRepository<SchoolApplication, Long> {
    // 신청 상태(PENDING, APPROVED 등)에 따른 신청 목록 조회
    List<SchoolApplication> findByStatus(SchoolApplicationStatus status);

    // 이메일 신청 존재 여부 확인
    boolean existBySchoolEmail(String schoolEmail);
}