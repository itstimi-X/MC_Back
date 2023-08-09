package com.mini.mbti_collector.service;

import com.mini.mbti_collector.MailHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSenderImpl mailSender;

    public void mailSend(HttpSession session, String userEmail) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            Random random = new Random(System.currentTimeMillis());
            int result = 100000 + random.nextInt(900000);

            mailHandler.setTo(userEmail);
            mailHandler.setFrom("ssk0710402@gmail.com");
            mailHandler.setSubject("MBTI Collector 이메일 인증");

            String htmlContent = "<p>안녕하세요 MBTI Collector입니다.</p><br>"
                    + "<p>아래의 인증번호를 입력해주세요.</p><br>"
                    + "<p>인증번호 : " + result + "</p>";
            mailHandler.setText(htmlContent, true);
            mailHandler.send();

            session.setAttribute(userEmail, result);
            log.info("실행 시간 : " + (System.currentTimeMillis() / 1000.0 ));
        }
        catch (Exception e) {
            log.error("Error sending mail", e);
        }
    }
}
