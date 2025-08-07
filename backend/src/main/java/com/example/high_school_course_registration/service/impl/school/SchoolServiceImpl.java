package com.example.high_school_course_registration.service.impl.school;

import com.example.high_school_course_registration.dto.school.response.SchoolSimpleDto;
import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.repository.SchoolRepository;
import com.example.high_school_course_registration.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SchoolSimpleDto> getAllSchools() {
        List<School> schools = schoolRepository.findAllByDeletedAtIsNullOrderBySchoolNameAsc();

        List<SchoolSimpleDto> schoolDtos = schools.stream()
                .map(school -> SchoolSimpleDto.builder()
                        .id(school.getId())
                        .schoolCode(school.getSchoolCode())
                        .schoolName(school.getSchoolName())
                        .build())
                .collect(Collectors.toList());

        return schoolDtos;
    }
}