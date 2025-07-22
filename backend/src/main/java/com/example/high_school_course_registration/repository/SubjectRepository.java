package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.common.enums.SubjectStatus;
import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    // 특정 학교의 모든 과목 조회
    List<Subject> findBySchool(School school);

    // 특정 학교의 '상태'별 과목 조회 (예: 승인 대기중인 과목)
    List<Subject> findBySchoolAndSubjectStatus(School school, SubjectStatus status);
}