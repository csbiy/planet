package com.planet.dashboard.controller;

import com.planet.dashboard.controller.request.dto.RegisterForm;
import com.planet.dashboard.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
@Slf4j
public class RegisterController  {


    private final RegisterService registerService;

    @GetMapping
    public String getForm(Model model) {
        model.addAttribute("registerForm",new RegisterForm());
        return "register";
    }

    @PostMapping
    public String register(@Validated @ModelAttribute RegisterForm src ,BindingResult bindingResult ,Model model) {
        if(!isSamePw(src.getFirstPw(),src.getSecondPw())){
            model.addAttribute("isDiffPw",true);
        }
        if(bindingResult.hasErrors()){
            return "register";
        }
        return registerService.register(src,model);
    }


    private boolean isSamePw(String pw1 , String pw2){
        return pw1.equals(pw2);
    }



}
