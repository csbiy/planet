package com.planet.dashboard.controller;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.auth.EmailAuth;
import com.planet.dashboard.controller.request.dto.EmailValidationRequest;
import com.planet.dashboard.controller.response.dto.ValidationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
    public String sendEmail(@RequestParam String email , HttpServletRequest request){

        emailAuth.validate(email , request);
        return "ok";
    }

    @PostMapping(value = "/email-validate",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Header<ValidationResponse> validateEmail(@RequestBody EmailValidationRequest emailValRequest , HttpServletRequest request){
        HttpSession session = request.getSession(false);
        ValidationResponse response = new ValidationResponse();
        if(isNotVerified(emailValRequest.getAuth(), session)){
            response.setFail();
            return Header.response(response , "인증 실패");
        }
        response.setSuccess();
        return Header.response(response, "인증 성공");

    }

    private boolean isNotVerified(String auth, HttpSession session) {
        return session == null || !(session.getAttribute(SessionManager.AUTH_ID).equals(auth));
    }

}
