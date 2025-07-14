package com.example.high_school_course_registration.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "school_credential_issuance")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SchoolCredentialIssuance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_credential_issuance_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_application_id", unique = true, nullable = false)
    private SchoolApplication schoolApplication;

    @Column(name = "school_code", length = 30, nullable = false)
    private String schoolCode;

    @Column(name = "email_address", length = 255, nullable = false)
    private String emailAddress;

    @Column(name = "sent_at", updatable = false)
    private LocalDateTime sentAt;

    @PrePersist
    protected void onSent(){
        sentAt = LocalDateTime.now();
    }
}
