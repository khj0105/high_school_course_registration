package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Long> {

    Optional<StudentDetails> findByStudentNumber(String studentNumber);

    boolean existsByStudentNumber(String studentNumber);

    List<StudentDetails> findByClassroomId(Long classroomId);

    @Query("SELECT sd FROM StudentDetails sd JOIN sd.user u WHERE u.school = :school AND sd.studentGrade = :grade")
    List<StudentDetails> findBySchoolAndGrade(@Param("school") School school, @Param("grade") Long grade);
}