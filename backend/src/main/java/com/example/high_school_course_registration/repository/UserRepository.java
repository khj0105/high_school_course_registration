package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.common.enums.UserStatus;
import com.example.high_school_course_registration.common.enums.UserType;
import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findBySchoolAndUserTypeAndUserStatus(School school, UserType userType, UserStatus userStatus);

    List<User> findBySchoolAndUserType(School school, UserType userType);

    boolean existsByUserType(UserType userType);
}