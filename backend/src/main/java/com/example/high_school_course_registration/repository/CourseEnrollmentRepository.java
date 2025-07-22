package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.common.enums.EnrollmentApprovalStatus;
import com.example.high_school_course_registration.entity.Course;
import com.example.high_school_course_registration.entity.CourseEnrollment;
import com.example.high_school_course_registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {
    // 특정 학생의 모든 수강 내역 조회
    List<CourseEnrollment> findByStudent(User student);

    // 특정 강의의 모든 수강생 목록 조회
    List<CourseEnrollment> findByCourse(Course course);

    // 특정 학생이 특정 강의를 신청했는지 확인 (중복 신청 방지)
    Optional<CourseEnrollment> findByStudentAndCourse(User student, Course course);

    // 특정 강의의 승인된 수강생 수 카운트 (수강 정원 확인용)
    long countByCourseAndApprovalStatus(Course course, EnrollmentApprovalStatus status);

    // 특정 강의의 신청 상태별 수강생 목록 조회 (관리자/교사용)
    List<CourseEnrollment> findByCourseAndApprovalStatus(Course course, EnrollmentApprovalStatus status);
}