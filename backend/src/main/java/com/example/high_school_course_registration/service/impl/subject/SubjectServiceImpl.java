package com.example.high_school_course_registration.service.impl.subject;

import com.example.high_school_course_registration.common.enums.SubjectStatus;
import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.dto.subject.request.SubjectApprovalRequestDto;
import com.example.high_school_course_registration.dto.subject.request.SubjectCreateRequestDto;
import com.example.high_school_course_registration.dto.subject.request.SubjectUpdateRequestDto;
import com.example.high_school_course_registration.dto.subject.response.SubjectDetailDto;
import com.example.high_school_course_registration.dto.subject.response.SubjectSimpleDto;
import com.example.high_school_course_registration.entity.*;
import com.example.high_school_course_registration.repository.*;
import com.example.high_school_course_registration.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CourseScheduleRepository courseScheduleRepository;
    private final ClassroomRepository classroomRepository;

    @Override
    @Transactional
    public SubjectDetailDto createSubject(SubjectCreateRequestDto requestDto, String username) {
        User teacher = findUserByUsername(username);
        if (teacher.getSchool() == null) {
            throw new CustomException(ErrorCode.SUBJECT_CREATION_FORBIDDEN);
        }

        Subject subject = Subject.builder()
                .school(teacher.getSchool())
                .teacher(teacher)
                .subjectName(requestDto.getSubjectName())
                .subjectGrade(Long.valueOf(requestDto.getGrade()))
                .subjectSemester(Long.valueOf(requestDto.getSemester()))
                .subjectType(requestDto.getSubjectType())
                .subjectCredits(Long.valueOf(requestDto.getCredits()))
                .subjectAffiliation(requestDto.getSubjectAffiliation())
                .subjectStatus(SubjectStatus.PENDING)
                .build();
        return convertToDetailDto(subjectRepository.save(subject));
    }

    @Override
    @Transactional
    public SubjectDetailDto updateMySubject(Long subjectId, SubjectUpdateRequestDto requestDto, String username) {
        User teacher = findUserByUsername(username);
        Subject subject = findSubjectById(subjectId);
        if (!subject.getTeacher().equals(teacher)) {
            throw new CustomException(ErrorCode.SUBJECT_UPDATE_FORBIDDEN);
        }
        if (subject.getSubjectStatus() != SubjectStatus.PENDING) {
            throw new CustomException(ErrorCode.SUBJECT_UPDATE_INVALID_STATUS);
        }
        subject.updateDetails(
                requestDto.getSubjectName(),
                requestDto.getGrade(),
                requestDto.getSemester(),
                requestDto.getSubjectType(),
                requestDto.getCredits(),
                requestDto.getSubjectAffiliation()
        );
        return convertToDetailDto(subject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectSimpleDto> getMySubjects(String username) {
        User teacher = findUserByUsername(username);
        return subjectRepository.findByTeacher(teacher).stream()
                .map(this::convertToSimpleDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectDetailDto getMySubjectById(Long subjectId, String username) {
        User teacher = findUserByUsername(username);
        Subject subject = findSubjectById(subjectId);
        if (!subject.getTeacher().equals(teacher)) {
            throw new CustomException(ErrorCode.SUBJECT_VIEW_FORBIDDEN);
        }
        return convertToDetailDto(subject);
    }

    @Override
    @Transactional
    public SubjectDetailDto updateSubjectStatus(Long subjectId, SubjectApprovalRequestDto requestDto, String username) {
        User admin = findUserByUsername(username);
        Subject subject = findSubjectById(subjectId);

        if (!subject.getSchool().equals(admin.getSchool())) {
            throw new CustomException(ErrorCode.SUBJECT_MANAGE_FORBIDDEN);
        }

        subject.updateStatus(requestDto.getStatus());

        if (requestDto.getStatus() == SubjectStatus.APPROVED) {
            if (requestDto.getSchedules() == null || requestDto.getSchedules().isEmpty()) {
                throw new CustomException(ErrorCode.SCHEDULE_REQUIRED_FOR_APPROVAL);
            }

            Course course = Course.builder()
                    .school(subject.getSchool())
                    .subject(subject)
                    .teacher(subject.getTeacher())
                    .year(LocalDate.now().getYear())
                    .semester(subject.getSubjectSemester())
                    .courseMaxEnrollment(30)
                    .build();
            Course savedCourse = courseRepository.save(course);

            for (SubjectApprovalRequestDto.ScheduleInfo scheduleInfo : requestDto.getSchedules()) {
                Classroom classroom = classroomRepository.findById(scheduleInfo.getClassroomId())
                        .orElseThrow(() -> new CustomException(ErrorCode.CLASSROOM_NOT_FOUND));
                CourseSchedule schedule = CourseSchedule.builder()
                        .course(savedCourse)
                        .dayOfWeek(scheduleInfo.getDayOfWeek())
                        .period(scheduleInfo.getPeriod())
                        .classroom(classroom)
                        .build();
                courseScheduleRepository.save(schedule);
            }
        }
        return convertToDetailDto(subject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectSimpleDto> getAllSubjectsInSchool(String username) {
        User admin = findUserByUsername(username);
        return subjectRepository.findBySchool(admin.getSchool()).stream()
                .map(this::convertToSimpleDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectDetailDto getSubjectById(Long subjectId, String username) {
        User admin = findUserByUsername(username);
        Subject subject = findSubjectById(subjectId);
        if (!subject.getSchool().equals(admin.getSchool())) {
            throw new CustomException(ErrorCode.SUBJECT_MANAGE_FORBIDDEN);
        }
        return convertToDetailDto(subject);
    }
        // --- Private Helper Methods ---

        private User findUserByUsername(String username) {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        }

        private Subject findSubjectById(Long subjectId) {
            return subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new CustomException(ErrorCode.SUBJECT_NOT_FOUND));
        }

        private SubjectDetailDto convertToDetailDto(Subject subject) {
            return SubjectDetailDto.builder()
                    .id(subject.getId())
                    .schoolId(subject.getSchool().getId())
                    .teacherId(subject.getTeacher().getId()) // 교사 ID 추가
                    .subjectName(subject.getSubjectName())
                    .grade(subject.getSubjectGrade().intValue())
                    .semester(subject.getSubjectSemester().intValue())
                    .subjectType(subject.getSubjectType())
                    .credits(subject.getSubjectCredits().intValue())
                    .subjectAffiliation(subject.getSubjectAffiliation())
                    .status(subject.getSubjectStatus())
                    .build();
        }

        private SubjectSimpleDto convertToSimpleDto(Subject subject) {
            return SubjectSimpleDto.builder()
                    .id(subject.getId())
                    .subjectName(subject.getSubjectName())
                    .teacherName(subject.getTeacher().getName()) // 교사 이름 추가
                    .grade(subject.getSubjectGrade().intValue())
                    .semester(subject.getSubjectSemester().intValue())
                    .subjectAffiliation(subject.getSubjectAffiliation())
                    .status(subject.getSubjectStatus())
                    .build();
        }
    }