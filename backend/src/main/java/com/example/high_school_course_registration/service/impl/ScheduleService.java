package com.example.high_school_course_registration.service.impl;

import com.example.high_school_course_registration.dto.schedule.response.ScheduleResponseDto;
import com.example.high_school_course_registration.entity.CourseRegistration;
import com.example.high_school_course_registration.entity.Lecture;
import com.example.high_school_course_registration.entity.Student;
import com.example.high_school_course_registration.repository.CourseRegistrationRepository;
import com.example.high_school_course_registration.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final StudentRepository studentRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;

    public List<ScheduleResponseDto> getSchedule(String email) {
        Student student = studentRepository.findByStudentEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 학생을 찾을 수 없습니다."));

        List<CourseRegistration> registrations = courseRegistrationRepository.findByStudentAndStatus(student, CourseRegistrationStatus.CONFIRMED);

        return registrations.stream()
                .map(registration -> {
                    Lecture lecture = registration.getLecture();
                    return ScheduleResponseDto.builder()
                            .dayOfWeek(lecture.getDayOfWeek().name())
                            .period(lecture.getPeriod())
                            .subjectName(lecture.getSubject().getSubjectName())
                            .lectureRoom(lecture.getClassroom().getName())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
