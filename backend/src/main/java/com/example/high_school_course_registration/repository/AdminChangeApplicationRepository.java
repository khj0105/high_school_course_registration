package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.common.enums.ChangeStatus;
import com.example.high_school_course_registration.entity.AdminChangeApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminChangeApplicationRepository extends JpaRepository<AdminChangeApplication, Long> {

    List<AdminChangeApplication> findByChangeStatus(ChangeStatus status);
}
