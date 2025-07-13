package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.teacher.TeacherListGetResponseDto;

import java.util.List;

public interface TeacherService {
    // 선생 전체 조회 (반환 LIST) - 교사 / 관리자
    ResponseDto<List<TeacherListGetResponseDto>> getTeacherList(String username);
}
