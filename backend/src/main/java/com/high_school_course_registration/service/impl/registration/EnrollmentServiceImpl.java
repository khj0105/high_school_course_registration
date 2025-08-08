package com.high_school_course_registration.service.impl.registration;

import com.high_school_course_registration.common.enums.EnrollmentApprovalStatus;
import com.high_school_course_registration.common.enums.DayOfWeek;
import com.high_school_course_registration.common.exception.CustomException;
import com.high_school_course_registration.common.exception.ErrorCode;
import com.high_school_course_registration.dto.registration.request.EnrollmentRequestDto;
import com.high_school_course_registration.dto.registration.response.MyEnrollmentDto;
import com.high_school_course_registration.entity.*;
import com.high_school_course_registration.repository.*;
import high_school_course_registration.entity.*;
import high_school_course_registration.high_school_course_registration.repository.*;
import high_school_course_registration.repository.*;
import com.high_school_course_registration.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final SchoolPolicyRepository schoolPolicyRepository;
    private final CourseScheduleRepository courseScheduleRepository;

    @Override
    @Transactional
    public MyEnrollmentDto createEnrollment(String username, EnrollmentRequestDto requestDto) {
        User student = findUserByUsername(username);
        Course courseToEnroll = courseRepository.findById(requestDto.getCourseId())
                .orElseThrow(() -> new CustomException(ErrorCode.COURSE_NOT_FOUND));
        School school = student.getSchool();
        SchoolPolicy policy = schoolPolicyRepository.findBySchool(school)
                .orElseThrow(() -> new CustomException(ErrorCode.POLICY_NOT_FOUND));

        LocalDate today = LocalDate.now();
        if (today.isBefore(school.getApplicationStartedDay()) || today.isAfter(school.getApplicationLimitedDay())) {
            throw new CustomException(ErrorCode.REGISTRATION_PERIOD_NOT_ACTIVE);
        }

        if (courseEnrollmentRepository.existsByStudentAndCourse(student, courseToEnroll)) {
            throw new CustomException(ErrorCode.DUPLICATE_ENROLLMENT);
        }

        long currentEnrollmentCount = courseEnrollmentRepository.countByCourse(courseToEnroll);
        if (currentEnrollmentCount >= courseToEnroll.getCourseMaxEnrollment()) {
            throw new CustomException(ErrorCode.COURSE_CAPACITY_EXCEEDED);
        }

        List<CourseEnrollment> myEnrollments = courseEnrollmentRepository.findByStudent(student);
        long currentCredits = myEnrollments.stream()
                .mapToLong(enrollment -> enrollment.getCourse().getSubject().getSubjectCredits())
                .sum();
        if (currentCredits + courseToEnroll.getSubject().getSubjectCredits() > policy.getMaxCreditsSemester()) {
            throw new CustomException(ErrorCode.CREDITS_EXCEEDED);
        }

        List<CourseSchedule> mySchedules = courseScheduleRepository.findByCourseIn(
                myEnrollments.stream().map(CourseEnrollment::getCourse).collect(Collectors.toList()));
        List<CourseSchedule> newSchedules = courseScheduleRepository.findByCourse(courseToEnroll);

        for (CourseSchedule newSchedule : newSchedules) {
            for (CourseSchedule mySchedule : mySchedules) {
                if (newSchedule.getDayOfWeek() == mySchedule.getDayOfWeek() && newSchedule.getPeriod().equals(mySchedule.getPeriod())) {
                    throw new CustomException(ErrorCode.SCHEDULE_CONFLICT);
                }
            }
        }

        CourseEnrollment newEnrollment = CourseEnrollment.builder()
                .student(student)
                .course(courseToEnroll)
                .approvalStatus(EnrollmentApprovalStatus.PENDING)
                .build();

        return convertToMyEnrollmentDto(courseEnrollmentRepository.save(newEnrollment));
    }

    @Override
    @Transactional
    public void cancelEnrollment(String username, Long enrollmentId) {
        User student = findUserByUsername(username);
        CourseEnrollment enrollment = courseEnrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new CustomException(ErrorCode.ENROLLMENT_NOT_FOUND));

        if (!enrollment.getStudent().equals(student)) {
            throw new CustomException(ErrorCode.ENROLLMENT_PERMISSION_DENIED);
        }

        courseEnrollmentRepository.delete(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MyEnrollmentDto> getMyEnrollments(String username) {
        User student = findUserByUsername(username);
        return courseEnrollmentRepository.findByStudent(student).stream()
                .map(this::convertToMyEnrollmentDto)
                .collect(Collectors.toList());
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private MyEnrollmentDto convertToMyEnrollmentDto(CourseEnrollment enrollment) {
        Course course = enrollment.getCourse();
        DayOfWeek dayOfWeek = course.getSchedules().stream().findFirst().map(CourseSchedule::getDayOfWeek).orElse(null);
        int period = course.getSchedules().stream().findFirst().map(s -> s.getPeriod().intValue()).orElse(0);

        return MyEnrollmentDto.builder()
                .enrollmentId(enrollment.getId())
                .courseId(course.getId())
                .subjectName(course.getSubject().getSubjectName())
                .teacherName(course.getTeacher().getName())
                .dayOfWeek(dayOfWeek)
                .period(period)
                .approvalStatus(enrollment.getApprovalStatus())
                .build();
    }
}