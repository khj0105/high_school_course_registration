package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.common.enums.NoticeTargetAudience;
import com.example.high_school_course_registration.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    // 특정 학교의 공지사항 목록 페이징 조회
    Page<Notice> findBySchoolId(Long schoolId, Pageable pageable);

    // 특정 학교 및 대상(학생/교사/전체)에 따른 공지사항 목록 조회
    Page<Notice> findBySchoolIdAndNoticeTargetAudienceIn(Long schoolId, List<NoticeTargetAudience> audience, Pageable pageable);
}