package com.example.high_school_course_registration.service.impl.teacher;

import com.example.high_school_course_registration.common.ResponseMessage;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.teacher.response.TeacherSimpleDto;
import com.example.high_school_course_registration.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherManageServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public ResponseDto<List<TeacherSimpleDto>> getTeacherList(String username) {

        boolean isAuthorized = schoolRepository.existsBySchoolCode(username)
                || teacherRepository.existsByTeacherUsername(username);

        if (!isAuthorized) {
            throw new RuntimeException(ResponseMessage.NO_AUTHORITY + ": " + username);
        }

        List<Teacher> teachers = teacherRepository.findAll();

        List<TeacherSimpleDto> responseData = teachers.stream()
                .map(teacher -> TeacherSimpleDto.builder()
                        .teacherUsername(teacher.getTeacherUsername())
                        .teacherName(teacher.getTeacherName())
                        .teacherEmail(teacher.getTeacherEmail())
                        .teacherPhoneNumber(teacher.getTeacherPhoneNumber())
                        .teacherSubject(teacher.getTeacherSubject())
                        .teacherStatus(teacher.getTeacherStatus())
                        .build())
                .collect(Collectors.toList());
        return ResponseDto.setSuccess(ResponseMessage.GET_TEACHER_LIST_SUCCESS, responseData);
    }
}
