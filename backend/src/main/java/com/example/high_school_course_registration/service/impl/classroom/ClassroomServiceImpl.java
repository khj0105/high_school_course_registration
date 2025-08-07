package com.example.high_school_course_registration.service.impl.classroom;

import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.classroom.request.ClassroomRequestDto;
import com.example.high_school_course_registration.dto.classroom.response.ClassroomDto;
import com.example.high_school_course_registration.entity.Classroom;
import com.example.high_school_course_registration.entity.School;
import com.example.high_school_course_registration.repository.ClassroomRepository;
import com.example.high_school_course_registration.repository.SchoolRepository;
import com.example.high_school_course_registration.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final SchoolRepository schoolRepository;

    @Override
    @Transactional
    public ClassroomDto createClassroom(ClassroomRequestDto requestDto, String schoolCode) {
        School school = findSchoolByCode(schoolCode);

        Classroom classroom = Classroom.builder()
                .school(school)
                .classroomName(requestDto.getClassroomName())
                .locationInfo(requestDto.getLocationInfo())
                .build();

        Classroom savedClassroom = classroomRepository.save(classroom);
        return convertToDto(savedClassroom);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClassroomDto> getAllClassrooms(String schoolCode) {
        School school = findSchoolByCode(schoolCode);
        return classroomRepository.findBySchool(school).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClassroomDto updateClassroom(Long classroomId, ClassroomRequestDto requestDto, String schoolCode) {
        School school = findSchoolByCode(schoolCode);
        Classroom classroom = findClassroomByIdAndSchool(classroomId, school);

        classroom.updateClassroomInfo(requestDto.getClassroomName(), requestDto.getLocationInfo());

        Classroom updatedClassroom = classroomRepository.save(classroom);
        return convertToDto(updatedClassroom);
    }

    @Override
    @Transactional
    public void deleteClassroom(Long classroomId, String schoolCode) {
        School school = findSchoolByCode(schoolCode);
        Classroom classroom = findClassroomByIdAndSchool(classroomId, school);
        classroomRepository.delete(classroom);
    }

    private ClassroomDto convertToDto(Classroom classroom) {
        return ClassroomDto.builder()
                .id(classroom.getId())
                .classroomName(classroom.getClassroomName())
                .locationInfo(classroom.getLocationInfo())
                .build();
    }

    private School findSchoolByCode(String schoolCode) {
        return schoolRepository.findBySchoolCodeAndDeletedAtIsNull(schoolCode)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHOOL_NOT_FOUND));
    }

    private Classroom findClassroomByIdAndSchool(Long classroomId, School school) {
        Classroom classroom = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new CustomException(ErrorCode.CLASSROOM_NOT_FOUND));

        if (!classroom.getSchool().equals(school)) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
        return classroom;
    }
}