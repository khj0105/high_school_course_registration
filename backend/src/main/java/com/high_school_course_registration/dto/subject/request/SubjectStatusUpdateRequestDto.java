package com.high_school_course_registration.dto.subject.request;

import com.high_school_course_registration.common.enums.SubjectStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectStatusUpdateRequestDto {
    @NotNull(message = "과목 상태는 필수입니다.")
    private SubjectStatus status;
}