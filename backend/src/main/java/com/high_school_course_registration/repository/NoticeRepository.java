package com.high_school_course_registration.repository;

import com.high_school_course_registration.common.enums.NoticeTargetAudience;
import com.high_school_course_registration.entity.Notice;
import com.high_school_course_registration.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Page<Notice> findBySchoolId(School school, Pageable pageable);

    Page<Notice> findBySchoolIdAndNoticeTargetAudienceIn(School school, List<NoticeTargetAudience> audience, Pageable pageable);

    List<Notice> findBySchoolAndNoticeTargetAudienceInAndNoticeStartDateLessThanEqualAndNoticeEndDateGreaterThanEqualOrderByCreatedAtDesc(
            School school, List<NoticeTargetAudience> audiences, LocalDate now1, LocalDate now2);
}