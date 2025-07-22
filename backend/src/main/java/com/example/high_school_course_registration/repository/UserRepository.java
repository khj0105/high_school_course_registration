package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.common.enums.UserStatus;
import com.example.high_school_course_registration.common.enums.UserType;
import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 로그인 및 아이디/이메일 중복 검사
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);

    // 특정 학교의 '역할' 및 '상태'별 사용자 목록 조회
    List<User> findBySchoolAndUserTypeAndUserStatus(School school, UserType userType, UserStatus userStatus);
    List<User> findBySchoolAndUserType(School school, UserType userType);
}