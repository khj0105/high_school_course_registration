package com.example.high_school_course_registration.controller.superadmin;

import com.example.high_school_course_registration.common.ApiMappingPattern;
import com.example.high_school_course_registration.dto.common.ResponseDto;
import com.example.high_school_course_registration.dto.school.response.SchoolApplicationDto;
import com.example.high_school_course_registration.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.SUPER_ADMIN_SCHOOL_APPLICATIONS) // /api/v2/super-admin/school-applications
@RequiredArgsConstructor
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<SchoolApplicationDto>>> getPendingApplications() {
        List<SchoolApplicationDto> applications = superAdminService.getPendingApplications();
        return ResponseEntity.ok(ResponseDto.setSuccess("승인 대기 목록 조회 성공", applications));
    }

    @PatchMapping("/{applicationId}/approve")
    public ResponseEntity<ResponseDto<Void>> approveSchoolApplication(@PathVariable Long applicationId) {
        superAdminService.approveSchoolApplication(applicationId);
        return ResponseEntity.ok(ResponseDto.setSuccess("학교 신청이 승인되었습니다.", null));
    }

    @PatchMapping("/{applicationId}/reject")
    public ResponseEntity<ResponseDto<Void>> rejectSchoolApplication(@PathVariable Long applicationId) {
        superAdminService.rejectSchoolApplication(applicationId);
        return ResponseEntity.ok(ResponseDto.setSuccess("학교 신청이 거절되었습니다.", null));
    }
}