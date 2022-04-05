package com.planet.dashboard.controller;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.controller.request.dto.NickNameValidationRequest;
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
@RequestMapping("/api/register")
public class ValidateApiController {

    private final EmailHandler emailHandler;
    private final UserRepository userRepository;


    @GetMapping("/email-send")
    public Header<ValidationResponse> sendEmail(@RequestParam String email , HttpServletRequest request){

        ValidationResponse response = new ValidationResponse();
        if(userRepository.findByEmail(email).isPresent()){
                response.setDuplicate("이미 존재하는 회원입니다.");
                return Header.response(response);
        };
        response.setWaiting("");
        emailHandler.send(email , request);
        return Header.response(response);
    }

    @PostMapping(value = "/email-validate",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Header<ValidationResponse> validateEmail(@RequestBody EmailValidationRequest emailValRequest , HttpSession session){
        ValidationResponse response = new ValidationResponse();

        if(emailNotVerified( emailValRequest.getAuth() , session )){
            response.setFail("인증 실패");
            return Header.response(response );
        }
        response.setSuccess("인증 성공");
        return Header.response(response);

    }

    @PostMapping("nickname")
    public Header<ValidationResponse> validateNickName(@RequestBody NickNameValidationRequest nickNameValidationRequest){
        ValidationResponse response = new ValidationResponse();
        if(isExistNickName(nickNameValidationRequest.getNickName())){
            response.setDuplicate("중복된 닉네임입니다. 다른 닉네임을 입력해주세요.");
            return Header.response(response);
        }
        response.setSuccess("사용 가능한 닉네임입니다.");
        return Header.response(response);
    }

    private boolean emailNotVerified(String auth, HttpSession session) {
        String actualInput = auth.trim();
        Object emailSession = SessionManager.getSession(session, SessionManager.EMAIL_AUTH);
        return  ( session == null ||   emailSession == null || !(((EmailSession) emailSession).getAuth().equals(actualInput)) );
    }


    private boolean isExistNickName(String nickName){
        return userRepository.findByNickName(nickName).size() > 0 ;
    }

}
