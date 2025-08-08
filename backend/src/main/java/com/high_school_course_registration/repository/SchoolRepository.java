package com.high_school_course_registration.repository;

import com.high_school_course_registration.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long>, JpaSpecificationExecutor<School> {

    Optional<School> findBySchoolCodeAndDeletedAtIsNull(String schoolCode);

    boolean existsBySchoolCode(String schoolCode);

    List<School> findAllByDeletedAtIsNullOrderBySchoolNameAsc();

    Optional<School> findByIdAndDeletedAtIsNull(Long id);
}