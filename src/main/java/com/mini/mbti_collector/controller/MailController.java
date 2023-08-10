package com.mini.mbti_collector.controller;

import com.mini.mbti_collector.service.MailSendService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailSendService mailSendService;

    private static final Logger log = LoggerFactory.getLogger(MailController.class);


    @PostMapping(value = "/api/email-verification", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mailCheck(@RequestBody HashMap<String, Object> user) {
        String email = (String) user.get("email");
        String authNum = mailSendService.joinEmail(email);

        log.info("email : " + user.get("email"));
        log.info("checkNum : " + authNum);

        return ResponseEntity.status(HttpStatus.OK).body(authNum);
    }
}
