package com.planet.dashboard.controller;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.controller.request.dto.EmailForm;
import com.planet.dashboard.controller.request.dto.RegisterForm;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    public String getRegisterForm(Model model) {
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("registerForm",registerForm);
        return "register";
    }

    @PostMapping
    public String register(@Validated @ModelAttribute RegisterForm src , BindingResult bindingResult , HttpServletRequest request , Model model, HttpSession session) {

        if(SessionManager.getSession(session, SessionManager.NICKNAME_AUTH) == null){
            model.addAttribute("isNickNameValid",true);
            return "register";
        }
        if(!isSamePw(src.getFirstPw(),src.getSecondPw())){
            model.addAttribute("isDiffPw",true);
            return "register";
        }
        if(bindingResult.hasErrors()){
            return "register";
        }
        User registeredUser = registerService.register(src, request.getSession());
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registeredUser.getEmail(), registeredUser.getPassword() , List.of(()->"ROLE_USER"));
        SecurityContextHolder.getContext().setAuthentication(authToken); // 회원가입 후 자동로그인
        return "index";
    }


    private boolean isSamePw(String pw1 , String pw2){
        return pw1.equals(pw2);
    }



}
