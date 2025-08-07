package com.example.high_school_course_registration.service.impl.auth;

import com.example.high_school_course_registration.common.exception.CustomException;
import com.example.high_school_course_registration.common.exception.ErrorCode;
import com.example.high_school_course_registration.provider.JwtProvider;
import com.example.high_school_course_registration.service.EmailAuthService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailAuthServiceImpl implements EmailAuthService {

    private final JavaMailSender mailSender;
    private final JwtProvider jwtProvider;

    @Override
    public void sendVerificationEmail(String email) {
        String token = jwtProvider.generateEmailToken(email);

        String verificationLink = "http://localhost:3000/auth/verify-email?token=" + token;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("[고교학점제] 이메일 인증을 완료해주세요.");

            String htmlContent = "<h1>이메일 인증</h1>" +
                    "<p>아래 버튼을 클릭하여 이메일 인증을 완료해주세요.</p>" +
                    "<a href=\"" + verificationLink + "\" style=\"padding: 10px 20px; color: white; background-color: #007bff; text-decoration: none; border-radius: 5px;\">이메일 인증하기</a>";
            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송에 실패했습니다.", e);
        }
    }

    @Override
    public void verifyEmailCode(String email, String code) {
        if (!jwtProvider.isValidToken(code)) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }

        String emailFromToken = jwtProvider.getUsernameFromJwt(code);
        if (!email.equals(emailFromToken)) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
    }
}