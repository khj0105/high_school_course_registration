package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.common.enums.EnrollmentApprovalStatus;
import com.example.high_school_course_registration.entity.Course;
import com.example.high_school_course_registration.entity.CourseEnrollment;
import com.example.high_school_course_registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {

    List<CourseEnrollment> findByStudent(User student);

    List<CourseEnrollment> findByCourse(Course course);

    boolean existsByStudentAndCourse(User student, Course course);

    long countByCourseAndApprovalStatus(Course course, EnrollmentApprovalStatus status);

    List<CourseEnrollment> findByCourseAndApprovalStatus(Course course, EnrollmentApprovalStatus status);

    List<CourseEnrollment> findByStudentAndApprovalStatus(User student, EnrollmentApprovalStatus status);

    long countByCourse(Course course);
}