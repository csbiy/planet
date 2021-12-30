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
public class RegisterController implements CrudController<RegisterForm> {


    private final RegisterService registerService;


    @GetMapping
    public String read(Model model) {
        model.addAttribute("registerForm",new RegisterForm());
        return "register";
    }

    @PostMapping
    public String create(@Validated @ModelAttribute RegisterForm src ,BindingResult bindingResult ,Model model) {
        if(!isSamePw(src.getFirstPw(),src.getSecondPw())){
            model.addAttribute("isDiffPw",true);
        }
        if(bindingResult.hasErrors()){
            return "register";
        }
        return registerService.register(src,model);
    }


    @Override
    public String update(RegisterForm src) {
        return null;
    }

    @Override
    public String delete(RegisterForm src) {
        return null;
    }

    private boolean isSamePw(String pw1 , String pw2){
        return pw1.equals(pw2);
    }



}
