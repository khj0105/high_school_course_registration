package com.example.high_school_course_registration.service.impl.policy;

import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.policy.request.PolicyUpdateRequestDto;
import com.example.high_school_course_registration.dto.policy.response.PolicyResponseDto;
import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.entity.SchoolPolicy;
import com.example.high_school_course_registration.repository.SchoolPolicyRepository;
import com.example.high_school_course_registration.repository.SchoolRepository;
import com.example.high_school_course_registration.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final SchoolPolicyRepository schoolPolicyRepository;
    private final SchoolRepository schoolRepository;

    @Override
    @Transactional(readOnly = true)
    public PolicyResponseDto getPolicy(String schoolCode) {
        School school = findSchoolByCode(schoolCode);
        SchoolPolicy policy = schoolPolicyRepository.findBySchool(school)
                .orElseThrow(() -> new CustomException(ErrorCode.POLICY_NOT_FOUND));

        return convertToDto(policy);
    }

    @Override
    @Transactional
    public PolicyResponseDto updatePolicy(PolicyUpdateRequestDto requestDto, String schoolCode) {
        School school = findSchoolByCode(schoolCode);

        SchoolPolicy policy = schoolPolicyRepository.findBySchool(school)
                .orElse(new SchoolPolicy(school));

        policy.update(requestDto.getMaxCreditsSemester(), requestDto.getGraduationCredits());

        SchoolPolicy savedPolicy = schoolPolicyRepository.save(policy);
        return convertToDto(savedPolicy);
    }

    private School findSchoolByCode(String schoolCode) {
        return schoolRepository.findBySchoolCodeAndDeletedAtIsNull(schoolCode)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHOOL_NOT_FOUND));
    }

    private PolicyResponseDto convertToDto(SchoolPolicy policy) {
        return PolicyResponseDto.builder()
                .maxCreditsSemester(policy.getMaxCreditsSemester().intValue())
                .graduationCredits(policy.getGraduationCredits().intValue())
                .build();
    }
}