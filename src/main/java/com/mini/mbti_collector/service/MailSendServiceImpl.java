package com.mini.mbti_collector.service;

import com.mini.mbti_collector.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class MailSendServiceImpl implements MailSendService {

    private static final Logger log = LoggerFactory.getLogger(MailSendServiceImpl.class);
    private static final int MAX_AUTH_NUMBER = 999999;
    private static final int MIN_AUTH_NUMBER = 100000;

    private final JavaMailSender javaMailSender; // JavaMailSender로 변경
    private final UserRepository userRepository;

    @Override
    public int makeRandomNumber() { // 반환 타입을 void에서 int로 변경
        Random random = new Random();
        int checkNum = random.nextInt(MAX_AUTH_NUMBER - MIN_AUTH_NUMBER + 1) + MIN_AUTH_NUMBER;
        log.info("Generated Authentication Number: {}", checkNum);
        return checkNum;
    }

    @Override
    public String joinEmail(String email, HttpSession session) {
        int authNumber = makeRandomNumber();

        String title = "[MBTI_Collector] 회원가입을 위한 인증메일입니다.";
        String message =
                "홈페이지를 방문해주셔서 감사합니다." +
                        "<br><br>" +
                        "인증번호는 " + authNumber + " 입니다." +
                        "<br><br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주시기바랍니다.";
        mailSend(message, email, title, session);
        session.setAttribute(""+email, authNumber);
        return Integer.toString(authNumber);
    }

    @Override
    public void mailSend(String message, String email, String title, HttpSession session) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(message,true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error sending email to {}", email, e);
            throw new RuntimeException("Failed to send email.", e); // 오류 메시지를 조금 더 구체적으로 변경
        }
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.findByEmail(email).isPresent(); // 이메일로 사용자를 조회하여 결과가 있으면 이미 등록된 것으로 판단합니다.
    }
}
