package com.its.crawlingtest.config.auth.controller;

import com.its.crawlingtest.config.auth.dto.SessionUser;
import com.its.crawlingtest.config.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final UserService userService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", userService.findAllDesc());

        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "login";
    }

//    @GetMapping("/login")
//    public String loginForm(){
//        return "login";
//    }
}
