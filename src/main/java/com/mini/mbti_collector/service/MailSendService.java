package com.mini.mbti_collector.service;

import jakarta.servlet.http.HttpSession;

public interface MailSendService {

    //    난수를 생성하는 메소드
    int makeRandomNumber();

    //    보낼 메일 양식을 만드는 메소드
    String joinEmail(String email, HttpSession session);

    //    메일을 보내는 메소드
    void mailSend(String message,String email, String title, HttpSession session);

    boolean isEmailRegistered(String email);

    boolean mailCertification(String email, String authNum, HttpSession session);
}