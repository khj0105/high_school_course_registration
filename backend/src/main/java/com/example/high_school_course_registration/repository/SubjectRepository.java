package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.common.enums.SubjectStatus;
import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.Subject;
import com.example.high_school_course_registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findBySchool(School school);

    List<Subject> findBySchoolAndSubjectStatus(School school, SubjectStatus status);

    List<Subject> findByTeacher(User teacher);
}