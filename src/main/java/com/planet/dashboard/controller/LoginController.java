package com.planet.dashboard.controller;

import com.planet.dashboard.controller.request.dto.LoginForm;
import com.planet.dashboard.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String addForm(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute(name = "loginForm") LoginForm loginForm ,  BindingResult bindingResult , Model model , HttpServletRequest request ){
        log.info("login info :{}",loginForm);
        if(bindingResult.hasErrors()) {
            return "login";
        }
        return loginService.login(request,loginForm,model);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        loginService.logout(request);
        return "redirect:/login";
    }
}
