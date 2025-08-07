package com.example.high_school_course_registration.service.impl.registration;

import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.registration.response.CourseEnrollmentSummaryDto;
import com.example.high_school_course_registration.dto.registration.response.EnrolledStudentDto;
import com.example.high_school_course_registration.entity.Course;
import com.example.high_school_course_registration.entity.CourseEnrollment;
import com.example.high_school_course_registration.repository.CourseEnrollmentRepository;
import com.example.high_school_course_registration.repository.CourseRepository;
import com.example.high_school_course_registration.service.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    private final CourseRepository courseRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;

    @Override
    @Transactional(readOnly = true)
    public CourseEnrollmentSummaryDto getEnrolledStudents(Long courseId, String username) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_FOUND));

        List<CourseEnrollment> enrollments = courseEnrollmentRepository.findByCourse(course);

        List<EnrolledStudentDto> studentDtos = enrollments.stream()
                .map(enrollment -> EnrolledStudentDto.builder()
                        .studentUserId(enrollment.getStudent().getId())
                        .studentNumber(enrollment.getStudent().getStudentDetails().getStudentNumber())
                        .studentName(enrollment.getStudent().getName())
                        .studentGrade(enrollment.getStudent().getStudentDetails().getStudentGrade().intValue())
                        .status(enrollment.getApprovalStatus())
                        .build())
                .collect(Collectors.toList());

        return CourseEnrollmentSummaryDto.builder()
                .courseId(course.getId())
                .subjectName(course.getSubject().getSubjectName())
                .teacherName(course.getTeacher().getName())
                .maxEnrollment(course.getCourseMaxEnrollment())
                .currentRegisteredCount(studentDtos.size())
                .enrolledStudents(studentDtos)
                .build();
    }
}