package com.high_school_course_registration.service;

import com.high_school_course_registration.common.enums.UserStatus;
import com.high_school_course_registration.common.enums.UserType;
import com.high_school_course_registration.entity.User;
import com.high_school_course_registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SuperAdminInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (!userRepository.existsByUserType(UserType.SUPER_ADMIN)) {


            User superAdmin = User.builder()
                    .username("superadmin")
                    .password(passwordEncoder.encode("superadmin123!"))
                    .name("최고관리자")
                    .email("super@admin.com")
                    .phoneNumber("010-0000-0000")
                    .userType(UserType.SUPER_ADMIN)
                    .userStatus(UserStatus.ACTIVE)
                    .build();

            userRepository.save(superAdmin);

            System.out.println("Super Admin account has been created.");
        }
    }
}