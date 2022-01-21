package com.planet.dashboard.controller;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.auth.EmailSession;
import com.planet.dashboard.controller.request.dto.EmailForm;
import com.planet.dashboard.controller.request.dto.RegisterForm;
import com.planet.dashboard.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
@Slf4j
public class RegisterController  {

    private final RegisterService registerService;

    @GetMapping("/email-validate")
    public String getEmailValidateForm(Model model){
        model.addAttribute("emailValidateForm",new EmailForm());
        return "emailValidate";
    }

    @GetMapping
    public String getRegisterForm(Model model,HttpSession session) {
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("registerForm",registerForm);
        return "register";
    }

    @PostMapping
    public String register(@Validated @ModelAttribute RegisterForm src , BindingResult bindingResult , HttpSession session , Model model) {
        if(!isSamePw(src.getFirstPw(),src.getSecondPw())){
            model.addAttribute("isDiffPw",true);
        }
        if(bindingResult.hasErrors()){
            return "register";
        }
        registerService.register(src,session);
        return "index";
    }


    private boolean isSamePw(String pw1 , String pw2){
        return pw1.equals(pw2);
    }



}
