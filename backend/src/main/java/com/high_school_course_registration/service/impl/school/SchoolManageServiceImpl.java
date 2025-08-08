package com.high_school_course_registration.service.impl.school;

import com.high_school_course_registration.common.exception.CustomException;
import com.high_school_course_registration.common.exception.ErrorCode;
import com.high_school_course_registration.dto.school.request.RegistrationPeriodRequestDto;
import com.high_school_course_registration.entity.School;
import com.high_school_course_registration.entity.User;
import com.high_school_course_registration.repository.SchoolRepository;
import com.high_school_course_registration.repository.UserRepository;
import com.high_school_course_registration.service.SchoolManageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchoolManageServiceImpl implements SchoolManageService {
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;

    @Override
    @Transactional
    public void setRegistrationPeriod(String adminUsername, RegistrationPeriodRequestDto requestDto) {
        User admin = userRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        School school = admin.getSchool();
        school.setRegistrationPeriod(requestDto.getStartDate(), requestDto.getEndDate());
        schoolRepository.save(school);
    }
}