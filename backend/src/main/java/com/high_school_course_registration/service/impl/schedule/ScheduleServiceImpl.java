package com.high_school_course_registration.service.impl.schedule;

import com.high_school_course_registration.common.enums.EnrollmentApprovalStatus;
import com.high_school_course_registration.common.enums.UserType;
import com.high_school_course_registration.common.exception.CustomException;
import com.high_school_course_registration.common.exception.ErrorCode;
import com.high_school_course_registration.dto.schedule.ScheduleDto;
import com.high_school_course_registration.entity.Course;
import com.high_school_course_registration.entity.CourseSchedule;
import com.high_school_course_registration.entity.User;
import com.high_school_course_registration.repository.CourseEnrollmentRepository;
import com.high_school_course_registration.repository.CourseRepository;
import com.high_school_course_registration.repository.CourseScheduleRepository;
import com.high_school_course_registration.repository.UserRepository;
import com.high_school_course_registration.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final CourseScheduleRepository courseScheduleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleDto> getMySchedule(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<Course> myCourses;

        if (user.getUserType() == UserType.STUDENT) {
            myCourses = courseEnrollmentRepository
                    .findByStudentAndApprovalStatus(user, EnrollmentApprovalStatus.APPROVED)
                    .stream()
                    .map(enrollment -> enrollment.getCourse())
                    .collect(Collectors.toList());
        } else if (user.getUserType() == UserType.TEACHER) {
            int currentYear = 2025;
            Long currentSemester = 1L;
            myCourses = courseRepository.findByTeacherAndYearAndSemester(user, currentYear, currentSemester);
        } else {
            return new ArrayList<>();
        }

        List<CourseSchedule> mySchedules = new ArrayList<>();
        for (Course course : myCourses) {
            mySchedules.addAll(courseScheduleRepository.findByCourse(course));
        }

        return mySchedules.stream()
                .map(schedule -> ScheduleDto.builder()
                        .courseId(schedule.getCourse().getId())
                        .dayOfWeek(schedule.getDayOfWeek())
                        .period(schedule.getPeriod().intValue())
                        .subjectName(schedule.getCourse().getSubject().getSubjectName())
                        .classroomName(schedule.getClassroom() != null ? schedule.getClassroom().getClassroomName() : "미정")
                        .build())
                .collect(Collectors.toList());
    }
}