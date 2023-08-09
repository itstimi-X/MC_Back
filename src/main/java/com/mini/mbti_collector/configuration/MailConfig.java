package com.mini.mbti_collector.configuration;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);  // SSL 포트로 변경
        mailSender.setUsername("ssk0710402@gmail.com");
        mailSender.setPassword("rffhomdpqplzohvl");

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.port", "465");  // SSL 포트로 설정
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  // SSL을 사용하기 위한 설정
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.debug", "true");

        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }
}
