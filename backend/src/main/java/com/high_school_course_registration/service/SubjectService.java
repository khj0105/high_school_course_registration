package com.high_school_course_registration.service;

import com.high_school_course_registration.dto.subject.request.SubjectApprovalRequestDto;
import com.high_school_course_registration.dto.subject.request.SubjectCreateRequestDto;
import com.high_school_course_registration.dto.subject.request.SubjectUpdateRequestDto;
import com.high_school_course_registration.dto.subject.response.SubjectDetailDto;
import com.high_school_course_registration.dto.subject.response.SubjectSimpleDto;

import java.util.List;

public interface SubjectService {

        SubjectDetailDto createSubject(SubjectCreateRequestDto requestDto, String username);
        SubjectDetailDto updateMySubject(Long subjectId, SubjectUpdateRequestDto requestDto, String username);
        List<SubjectSimpleDto> getMySubjects(String username);
        SubjectDetailDto getMySubjectById(Long subjectId, String username);


        SubjectDetailDto updateSubjectStatus(Long subjectId, SubjectApprovalRequestDto requestDto, String username);
        List<SubjectSimpleDto> getAllSubjectsInSchool(String username);
        SubjectDetailDto getSubjectById(Long subjectId, String username);
}