package com.high_school_course_registration.service.impl.notice;

import com.high_school_course_registration.common.enums.NoticeTargetAudience;
import com.high_school_course_registration.common.enums.UserType;
import com.high_school_course_registration.common.exception.CustomException;
import com.high_school_course_registration.common.exception.ErrorCode;
import com.high_school_course_registration.dto.notice.request.NoticeCreateRequestDto;
import com.high_school_course_registration.dto.notice.request.NoticeUpdateRequestDto;
import com.high_school_course_registration.dto.notice.response.NoticeDetailDto;
import com.high_school_course_registration.dto.notice.response.NoticeSimpleDto;
import com.high_school_course_registration.entity.Notice;
import com.high_school_course_registration.entity.School;
import com.high_school_course_registration.entity.User;
import com.high_school_course_registration.repository.NoticeRepository;
import com.high_school_course_registration.repository.UserRepository;
import com.high_school_course_registration.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public NoticeDetailDto createNotice(NoticeCreateRequestDto requestDto, String username) {
        User author = findUserByUsername(username);
        School school = author.getSchool();
        if (school == null) {
            throw new CustomException(ErrorCode.NOTICE_CREATION_FORBIDDEN);
        }

        Notice notice = Notice.builder()
                .school(school)
                .author(author)
                .noticeTitle(requestDto.getTitle())
                .noticeContent(requestDto.getContent())
                .noticeTargetAudience(requestDto.getTargetAudience())
                .noticeStartDate(requestDto.getStartDate())
                .noticeEndDate(requestDto.getEndDate())
                .build();

        Notice savedNotice = noticeRepository.save(notice);
        return convertToDetailDto(savedNotice);
    }

    @Override
    @Transactional
    public NoticeDetailDto updateNotice(Long noticeId, NoticeUpdateRequestDto requestDto, String username) {
        User author = findUserByUsername(username);
        Notice notice = findNoticeById(noticeId);

        if (!notice.getSchool().equals(author.getSchool())) {
            throw new CustomException(ErrorCode.NOTICE_UPDATE_FORBIDDEN);
        }

        notice.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getTargetAudience(), requestDto.getStartDate(), requestDto.getEndDate());
        return convertToDetailDto(notice);
    }

    @Override
    @Transactional
    public void deleteNotice(Long noticeId, String username) {
        User author = findUserByUsername(username);
        Notice notice = findNoticeById(noticeId);

        if (!notice.getSchool().equals(author.getSchool())) {
            throw new CustomException(ErrorCode.NOTICE_DELETE_FORBIDDEN);
        }

        noticeRepository.delete(notice);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoticeSimpleDto> getActiveNotices(String username) {
        User user = findUserByUsername(username);
        School school = user.getSchool();
        LocalDate now = LocalDate.now();

        List<NoticeTargetAudience> targetAudiences;
        if (user.getUserType() == UserType.STUDENT) {
            targetAudiences = List.of(NoticeTargetAudience.ALL, NoticeTargetAudience.STUDENT);
        } else {
            targetAudiences = List.of(NoticeTargetAudience.ALL, NoticeTargetAudience.STUDENT, NoticeTargetAudience.TEACHER);
        }

        List<Notice> notices = noticeRepository.findBySchoolAndNoticeTargetAudienceInAndNoticeStartDateLessThanEqualAndNoticeEndDateGreaterThanEqualOrderByCreatedAtDesc(
                school, targetAudiences, now, now);

        return notices.stream()
                .map(this::convertToSimpleDto)
                .collect(Collectors.toList());
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private Notice findNoticeById(Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));
    }

    private NoticeSimpleDto convertToSimpleDto(Notice notice) {
        return NoticeSimpleDto.builder()
                .id(notice.getId())
                .title(notice.getNoticeTitle())
                .authorName(notice.getAuthor().getName())
                .createdAt(notice.getCreatedAt())
                .build();
    }

    private NoticeDetailDto convertToDetailDto(Notice notice) {
        return NoticeDetailDto.builder()
                .id(notice.getId())
                .title(notice.getNoticeTitle())
                .content(notice.getNoticeContent())
                .authorName(notice.getAuthor().getName())
                .targetAudience(notice.getNoticeTargetAudience())
                .startDate(notice.getNoticeStartDate())
                .endDate(notice.getNoticeEndDate())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .build();
    }
}