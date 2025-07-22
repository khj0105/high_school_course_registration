package com.example.high_school_course_registration.repository;

import com.example.high_school_course_registration.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long>, JpaSpecificationExecutor<School> {
    /**
     * 학교 코드(실제 아이디)로 학교 정보를 조회합니다.
     * @param schoolCode 고유한 학교 아이디
     * @return Optional<School>
     */
    Optional<School> findBySchoolCode(String schoolCode);

    /**
     * 학교 코드(실제 아이디)의 존재 여부를 확인합니다. (중복 검사용)
     * @param schoolCode 고유한 학교 아이디
     * @return boolean
     */
    boolean existsBySchoolCode(String schoolCode);

    /**
     * [수정용] 회원가입 등에서 학교 목록을 보여주기 위해, 삭제되지 않은 학교를 정렬하여 조회합니다.
     * 여기서는 사용자 편의를 위해 '학교 이름' 오름차순으로 정렬했습니다.
     * @return List<School>
     */
    List<School> findAllByDeletedAtIsNullOrderBySchoolNameAsc();

    /**
     * ID(내부 관리 번호)로 삭제되지 않은 학교 정보를 조회합니다.
     * @param id 학교 ID (Primary Key)
     * @return Optional<School>
     */
    Optional<School> findByIdAndDeletedAtIsNull(Long id);
}





















