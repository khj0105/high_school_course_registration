    package com.high_school_course_registration.repository;

    import com.high_school_course_registration.common.enums.DayOfWeek;
    import com.high_school_course_registration.entity.Classroom;
    import com.high_school_course_registration.entity.Course;
    import com.high_school_course_registration.entity.CourseSchedule;
    import com.high_school_course_registration.entity.User;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

    public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, Long> {

        List<CourseSchedule> findByCourse(Course course);

        boolean existsByCourseTeacherAndCourseYearAndCourseSemesterAndDayOfWeekAndPeriod(User teacher, int year, Long semester, DayOfWeek dayOfWeek, Long period);

        boolean existsByClassroomAndCourseYearAndCourseSemesterAndDayOfWeekAndPeriod(Classroom classroom, int year, Long semester, DayOfWeek dayOfWeek, Long period);

        List<CourseSchedule> findByCourseIn(List<Course> courses);
    }