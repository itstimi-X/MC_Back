package com.mini.mbti_collector.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/users")
public class UserController {
    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public String example(){
        return "Success";
    }
}
