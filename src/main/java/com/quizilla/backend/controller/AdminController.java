package com.quizilla.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("quizilla/admin/login")
    public String login() {
        return "login";
    }

    @GetMapping("quizilla/admin/dashboard")
    public String dashboard() {
        return "dashboard";
    }

}
