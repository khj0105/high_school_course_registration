package com.high_school_course_registration.controller.schedule;

import com.high_school_course_registration.common.ApiMappingPattern;
import com.high_school_course_registration.dto.common.ResponseDto;
import com.high_school_course_registration.dto.schedule.ScheduleDto;
import com.high_school_course_registration.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.COMMON_SCHEDULE) // /api/v2/common/schedule
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/my")
    public ResponseEntity<ResponseDto<List<ScheduleDto>>> getMySchedule(
            @AuthenticationPrincipal String username) {

        List<ScheduleDto> schedule = scheduleService.getMySchedule(username);
        return ResponseEntity.ok(ResponseDto.setSuccess("나의 시간표 조회 성공", schedule));
    }
}