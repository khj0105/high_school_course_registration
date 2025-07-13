package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    boolean existsByTeacherUsername(String username);

    Optional<Teacher> getTeacherList(String username);
}