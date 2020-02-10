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

//    @PostMapping(value = "/quizilla/admin/doLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String doLogin(@RequestBody MultiValueMap<String, String> formData) {
//        if (formData == null) {
//            return redirectToLogin();
//        }
//
//        final String username = formData.getFirst("username");
//        final String password = formData.getFirst("password");
//        if ("pero".equalsIgnoreCase(username) && "pero".equalsIgnoreCase(password)) {
//            return redirectToDashboard();
//        }
//
//        return redirectToLogin();
//    }
//
//    private String redirectToLogin() {
//        return "redirect:/quizilla/admin/login";
//    }
//
//    private String redirectToDashboard() {
//        return "redirect:/quizilla/admin/dashboard";
//    }

}
