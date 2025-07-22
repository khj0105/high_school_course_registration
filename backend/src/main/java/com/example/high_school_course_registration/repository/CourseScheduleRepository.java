package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.common.enums.DayOfWeek;
import com.example.high_school_course_registration.entity.Classroom;
import com.example.high_school_course_registration.entity.CourseSchedule;
import com.example.high_school_course_registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Long> {
    // 특정 강의의 모든 시간표 조회
    List<CourseSchedule> findByCourseId(Long courseId);

    // 교사 시간표 중복 확인
    boolean existsByCourseTeacherAndCourseYearAndCourseSemesterAndDayOfWeekAndPeriod(User teacher, int year, Long semester, DayOfWeek dayOfWeek, Long period);

    // 강의실 시간표 중복 확인
    boolean existsByClassroomAndCourseYearAndCourseSemesterAndDayOfWeekAndPeriod(Classroom classroom, int year, Long semester, DayOfWeek dayOfWeek, Long period);
}