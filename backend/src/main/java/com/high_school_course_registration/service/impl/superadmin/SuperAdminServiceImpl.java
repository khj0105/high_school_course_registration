package com.high_school_course_registration.service.impl.superadmin;

import com.high_school_course_registration.common.enums.SchoolApplicationStatus;
import com.high_school_course_registration.common.enums.UserStatus;
import com.high_school_course_registration.common.enums.UserType;
import com.high_school_course_registration.common.exception.CustomException;
import com.high_school_course_registration.common.exception.ErrorCode;
import com.high_school_course_registration.dto.school.response.SchoolApplicationDto;
import com.high_school_course_registration.entity.School;
import com.high_school_course_registration.entity.SchoolApplication;
import com.high_school_course_registration.entity.SchoolCredentialIssuance;
import com.high_school_course_registration.entity.User;
import com.high_school_course_registration.repository.SchoolApplicationRepository;
import com.high_school_course_registration.repository.SchoolCredentialIssuanceRepository;
import com.high_school_course_registration.repository.SchoolRepository;
import com.high_school_course_registration.repository.UserRepository;
import com.high_school_course_registration.service.EmailService;
import com.high_school_course_registration.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuperAdminServiceImpl implements SuperAdminService {

    private final SchoolApplicationRepository schoolApplicationRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final SchoolCredentialIssuanceRepository schoolCredentialIssuanceRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<SchoolApplicationDto> getPendingApplications() {
        return schoolApplicationRepository.findBySchoolApplicationStatus(SchoolApplicationStatus.PENDING)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void approveSchoolApplication(Long applicationId) {
        SchoolApplication application = findApplicationById(applicationId);
        if (application.getSchoolApplicationStatus() != SchoolApplicationStatus.PENDING) {
            throw new CustomException(ErrorCode.ALREADY_PROCESSED_APPLICATION);
        }

        application.updateStatus(SchoolApplicationStatus.APPROVED);

        String schoolCode = generateSchoolCode(application.getSchoolName());
        String temporaryPassword = "changeme123!";

        School school = School.builder()
                .schoolName(application.getSchoolName())
                .schoolAddress(application.getSchoolAddress())
                .schoolContactNumber(application.getSchoolContactNumber())
                .schoolCode(schoolCode)
                .applicationStartedDay(LocalDate.now())
                .applicationLimitedDay(LocalDate.now().plusDays(7))
                .build();
        schoolRepository.save(school);

        User schoolAdmin = User.builder()
                .username(schoolCode)
                .password(passwordEncoder.encode(temporaryPassword))
                .email(application.getSchoolEmail())
                .phoneNumber(application.getSchoolAdminPhoneNumber())
                .name(application.getSchoolAdminName())
                .userType(UserType.SCHOOL_ADMIN)
                .school(school)
                .userStatus(UserStatus.ACTIVE)
                .build();
        userRepository.save(schoolAdmin);

        emailService.sendSchoolCredentials(application.getSchoolEmail(), schoolCode, temporaryPassword);

        SchoolCredentialIssuance issuance = SchoolCredentialIssuance.builder()
                .schoolApplication(application)
                .schoolCode(schoolCode)
                .emailAddress(application.getSchoolEmail())
                .build();
        schoolCredentialIssuanceRepository.save(issuance);
    }

    @Override
    @Transactional
    public void rejectSchoolApplication(Long applicationId) {
        SchoolApplication application = findApplicationById(applicationId);
        if (application.getSchoolApplicationStatus() != SchoolApplicationStatus.PENDING) {
            throw new CustomException(ErrorCode.ALREADY_PROCESSED_APPLICATION);
        }
        application.updateStatus(SchoolApplicationStatus.REJECTED);
    }

    private SchoolApplication findApplicationById(Long applicationId) {
        return schoolApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));
    }

    private String generateSchoolCode(String schoolName) {
        return schoolName.replaceAll("\\s+", "").toLowerCase() + "_" + UUID.randomUUID().toString().substring(0, 4);
    }

    private SchoolApplicationDto convertToDto(SchoolApplication application) {
        return SchoolApplicationDto.builder()
                .id(application.getId())
                .schoolName(application.getSchoolName())
                .schoolAdminName(application.getSchoolAdminName())
                .status(application.getSchoolApplicationStatus())
                .createdAt(application.getCreatedAt())
                .build();
    }
}