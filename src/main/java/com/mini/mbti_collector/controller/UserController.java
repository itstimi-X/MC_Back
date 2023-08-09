package com.mini.mbti_collector.controller;

import com.mini.mbti_collector.service.MailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5500")
public class UserController {
    MailService mailService;
    @Autowired
    public UserController(MailService mailService) {
        this.mailService = mailService;
    }
    @PostMapping(value = "/email-verification")
    private int sendEmail(HttpServletRequest request, @RequestParam String userEmail) {
        HttpSession session = request.getSession();
        mailService.mailSend(session, userEmail);
        return 123;
    }
}
