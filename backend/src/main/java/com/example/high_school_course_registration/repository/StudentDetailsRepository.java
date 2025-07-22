package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Long> {
    // 학번으로 학생 정보 조회
    Optional<StudentDetails> findByStudentNumber(String studentNumber);

    // 학번 존재 여부 확인
    boolean existsByStudentNumber(String studentNumber);

    // 특정 반(Classroom)에 소속된 학생 목록ㅈ ㅗ회
    List<StudentDetails> findByClassroomId(Long classroomId);

    // 특정 학교와 학년의 학생 목록 조회 (JPQL 사용)
    @Query("SELECT sd FROM StudentDetails sd JOIN sd.user u WHERE u.school = :school AND sd.studentGrade = :grade")
    List<StudentDetails> findBySchoolAndGrade(@Param("school") School school, @Param("grade") Long grade);
}