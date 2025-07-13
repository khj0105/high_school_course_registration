package com.example.high_school_course_registration.service;

import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.lecture.request.LectureUpdateRequestDto;
import com.example.high_school_course_registration.dto.lecture.response.LectureDetailDto;
import com.example.high_school_course_registration.dto.lecture.response.LectureListDto;

import java.util.List;

public interface LectureService {
    ResponseDto<List<LectureListDto>> searchLectures(String username, Long lectureId, String subjectName, String teacherName, LectureDayOfWeek dayOfWeek, int period, int allowedGrade);
    ResponseDto<LectureDetailDto> findByStudentId(String username, Long lectureId);
    ResponseDto<LectureListDto> updateLecture(String username, Long lectureId, LectureUpdateRequestDto requestDto);
    ResponseDto<?> deleteLecture(String username, Long lectureId);
}