package com.planet.dashboard.controller;

import com.planet.dashboard.dto.LoginForm;
import com.planet.dashboard.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public String addForm(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "login";
    }

    @PostMapping
    public String getForm(HttpServletRequest request , LoginForm loginForm){
        return loginService.login(request,loginForm);
    }
}
