package com.high_school_course_registration.repository;

import com.high_school_course_registration.entity.Classroom;
import com.high_school_course_registration.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    List<Classroom> findBySchool(School school);
}