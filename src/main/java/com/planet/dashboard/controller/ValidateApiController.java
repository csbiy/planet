package com.planet.dashboard.controller;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.email.EmailHandler;
import com.planet.dashboard.email.EmailSession;
import com.planet.dashboard.controller.request.dto.EmailValidationRequest;
import com.planet.dashboard.controller.response.dto.ValidationResponse;
import com.planet.dashboard.repository.UserRepository;
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

    private final EmailHandler emailHandler;
    private final UserRepository userRepository;


    @GetMapping("/email-send")
    public Header<ValidationResponse> sendEmail(@RequestParam String email , HttpServletRequest request){

        ValidationResponse response = new ValidationResponse();
        if(userRepository.findById(email).isPresent()){
                response.setDuplicate();
                return Header.response(response);
        };
        response.setWaiting();
        emailHandler.send(email , request);
        return Header.response(response);
    }

    @PostMapping(value = "/email-validate",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Header<ValidationResponse> validateEmail(@RequestBody EmailValidationRequest emailValRequest , HttpSession session){
        ValidationResponse response = new ValidationResponse();

        if(isNotVerified( emailValRequest.getAuth() , session )){
            response.setFail();
            return Header.response(response , "인증 실패");
        }
        response.setSuccess();
        return Header.response(response, "인증 성공");

    }

    private boolean isNotVerified(String auth, HttpSession session) {
        String actualInput = auth.trim();
        Object emailSession = SessionManager.getSession(session, SessionManager.EMAIL_AUTH);
        return  ( session == null ||   emailSession == null || !(((EmailSession) emailSession).getAuth().equals(actualInput)) );
    }



}
