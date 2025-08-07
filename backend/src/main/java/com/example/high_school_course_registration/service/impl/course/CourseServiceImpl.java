package com.example.high_school_course_registration.service.impl.course;

import com.example.high_school_course_registration.common.enums.EnrollmentApprovalStatus;
import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.course.request.CourseCreateRequestDto;
import com.example.high_school_course_registration.dto.course.request.CourseUpdateRequestDto;
import com.example.high_school_course_registration.dto.course.response.CourseDetailDto;
import com.example.high_school_course_registration.dto.course.response.CourseSimpleDto;
import com.example.high_school_course_registration.dto.schedule.ScheduleDto;
import com.example.high_school_course_registration.entity.*;
import com.example.high_school_course_registration.repository.*;
import com.example.high_school_course_registration.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;

    @Override
    @Transactional
    public CourseDetailDto createCourse(CourseCreateRequestDto requestDto, String username) {
        User user = findUserByUsername(username);
        School school = user.getSchool();
        if (school == null) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        Subject subject = subjectRepository.findById(requestDto.getSubjectId())
                .orElseThrow(() -> new CustomException(ErrorCode.SUBJECT_NOT_FOUND));

        User teacher = userRepository.findById(requestDto.getTeacherId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Course course = Course.builder()
                .school(school)
                .subject(subject)
                .teacher(teacher)
                .year(requestDto.getYear())
                .semester(Long.valueOf(requestDto.getSemester()))
                .courseMaxEnrollment(requestDto.getMaxEnrollment())
                .build();

        return convertToDetailDto(courseRepository.save(course));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseSimpleDto> getAllCourses(String username) {
        User user = findUserByUsername(username);
        return courseRepository.findBySchool(user.getSchool()).stream()
                .map(this::convertToSimpleDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDetailDto getCourseById(Long courseId, String username) {
        User user = findUserByUsername(username);
        Course course = findCourseById(courseId);

        if (!course.getSchool().equals(user.getSchool())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
        return convertToDetailDto(course);
    }

    @Override
    @Transactional
    public CourseDetailDto updateCourse(Long courseId, CourseUpdateRequestDto requestDto, String username) {
        User user = findUserByUsername(username);
        Course course = findCourseById(courseId);

        if (!course.getSchool().equals(user.getSchool())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        User teacher = userRepository.findById(requestDto.getTeacherId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        course.update(teacher, requestDto.getMaxEnrollment(), requestDto.getDescription());
        return convertToDetailDto(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Long courseId, String username) {
        User user = findUserByUsername(username);
        Course course = findCourseById(courseId);

        if (!course.getSchool().equals(user.getSchool())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        courseRepository.delete(course);
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_FOUND));
    }

    private CourseDetailDto convertToDetailDto(Course course) {
        List<ScheduleDto> scheduleDtos = course.getSchedules().stream()
                .map(schedule -> ScheduleDto.builder()
                        .courseId(course.getId())
                        .dayOfWeek(schedule.getDayOfWeek())
                        .period(schedule.getPeriod().intValue())
                        .classroomName(schedule.getClassroom() != null ? schedule.getClassroom().getClassroomName() : "미정")
                        .subjectName(course.getSubject().getSubjectName())
                        .build())
                .collect(Collectors.toList());

        int currentEnrollment = (int) courseEnrollmentRepository.countByCourseAndApprovalStatus(course, EnrollmentApprovalStatus.APPROVED);

        return CourseDetailDto.builder()
                .id(course.getId())
                .subjectName(course.getSubject().getSubjectName())
                .description(course.getDescription())
                .teacherName(course.getTeacher().getName())
                .maxEnrollment(course.getCourseMaxEnrollment())
                .currentEnrollment(currentEnrollment)
                .schedules(scheduleDtos)
                .build();
    }

    private CourseSimpleDto convertToSimpleDto(Course course) {
        CourseSchedule firstSchedule = course.getSchedules().stream().findFirst().orElse(null);

        return CourseSimpleDto.builder()
                .id(course.getId())
                .subjectName(course.getSubject().getSubjectName())
                .teacherName(course.getTeacher().getName())
                .grade(course.getSubject().getSubjectGrade().intValue())
                .dayOfWeek(firstSchedule != null ? firstSchedule.getDayOfWeek() : null)
                .period(firstSchedule != null ? firstSchedule.getPeriod().intValue() : 0)
                .build();
    }
}