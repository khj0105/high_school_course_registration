package com.example.high_school_course_registration.service.impl.syllabus;

import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.syllabus.request.SyllabusUpdateRequestDto;
import com.example.high_school_course_registration.dto.syllabus.response.SyllabusResponseDto;
import com.example.high_school_course_registration.entity.Course;
import com.example.high_school_course_registration.entity.Syllabus;
import com.example.high_school_course_registration.entity.User;
import com.example.high_school_course_registration.repository.CourseRepository;
import com.example.high_school_course_registration.repository.SyllabusRepository;
import com.example.high_school_course_registration.repository.UserRepository;
import com.example.high_school_course_registration.service.SyllabusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SyllabusServiceImpl implements SyllabusService {

    private final SyllabusRepository syllabusRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public SyllabusResponseDto getSyllabus(Long courseId, String username) {
        Course course = findCourseAndVerifyTeacher(courseId, username);
        Syllabus syllabus = syllabusRepository.findByCourse(course)
                .orElseThrow(() -> new CustomException(ErrorCode.SYLLABUS_NOT_FOUND));
        return convertToDto(syllabus);
    }

    @Override
    @Transactional
    public SyllabusResponseDto updateSyllabus(Long courseId, SyllabusUpdateRequestDto requestDto, String username) {
        Course course = findCourseAndVerifyTeacher(courseId, username);

        Syllabus syllabus = syllabusRepository.findByCourse(course)
                .orElse(new Syllabus(course));

        syllabus.update(requestDto.getContent(), requestDto.getLearningObjectives(), requestDto.getGradingPolicy());

        Syllabus savedSyllabus = syllabusRepository.save(syllabus);
        return convertToDto(savedSyllabus);
    }

    private Course findCourseAndVerifyTeacher(Long courseId, String username) {
        User teacher = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_FOUND));

        if (!course.getTeacher().equals(teacher)) {
            throw new CustomException(ErrorCode.SYLLABUS_PERMISSION_DENIED);
        }
        return course;
    }

    private SyllabusResponseDto convertToDto(Syllabus syllabus) {
        return SyllabusResponseDto.builder()
                .syllabusId(syllabus.getId())
                .courseId(syllabus.getCourse().getId())
                .content(syllabus.getContent())
                .learningObjectives(syllabus.getLearningObjectives())
                .gradingPolicy(syllabus.getGradingPolicy())
                .build();
    }
}