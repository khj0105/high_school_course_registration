package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    // 특정 학교의 모든 강의실 조회
    List<Classroom> findBySchoolId(Long schoolId);
}