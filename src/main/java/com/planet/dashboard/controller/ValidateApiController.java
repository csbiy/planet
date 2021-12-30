package com.planet.dashboard.controller;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.auth.EmailAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/register")
public class ValidateApiController {

    private final EmailAuth emailAuth;

    @GetMapping("/email-send")
    @ResponseBody
    public String sendEmail(@RequestParam String email , HttpServletRequest request){
        log.info("validate target email : {}", email);
        emailAuth.validate(email , request);
        return "ok";
    }

    @GetMapping("/email-validate")
    @ResponseBody
    public String validateEmail(@RequestParam String auth , HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(isNotVerified(auth, session)){
            //TODO : 반환 결과메시지를 message resolver로 가져올려고 함.
            return "인증 실패하였습니다.";
        }
        return "인증 성공하였습니다. ";
    }

    private boolean isNotVerified(String auth, HttpSession session) {
        return session == null || !(session.getAttribute(SessionManager.AUTH_ID).equals(auth));
    }

}
