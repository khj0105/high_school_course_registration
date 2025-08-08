package com.high_school_course_registration.service.impl;

import com.high_school_course_registration.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendSchoolCredentials(String toEmail, String schoolCode, String temporaryPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("[고교학점제] 학교 등록이 승인되었습니다.");
            String htmlContent = "<h1>학교 등록 승인 안내</h1>" +
                    "<p>고교학점제 시스템 등록이 성공적으로 승인되었습니다.</p>" +
                    "<p>아래 정보를 사용하여 학교 관리자로 로그인해주세요.</p>" +
                    "<p><b>아이디:</b> " + schoolCode + "</p>" +
                    "<p><b>임시 비밀번호:</b> " + temporaryPassword + "</p>" +
                    "<p>로그인 후 반드시 비밀번호를 변경해주시기 바랍니다.</p>";
            helper.setText(htmlContent, true);
            mailSender.send(message);
        } catch (MessagingException e) {

            throw new RuntimeException("자격 증명 이메일 발송에 실패했습니다.", e);
        }
    }
}