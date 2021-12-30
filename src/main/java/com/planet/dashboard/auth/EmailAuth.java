package com.planet.dashboard.auth;

import com.planet.dashboard.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.SendFailedException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class EmailAuth {

    private final EmailService emailServiceImpl;

    public void validate(String email , HttpServletRequest request){
        String authNum = emailServiceImpl.sendTemplateMessage(email);
        //TODO : 고려사항 - 세션? Cookie? ->  쿠키의 경우 값이 그대로 보임 (쿠키를 양방향암호화)?
        HttpSession session = request.getSession();
        SessionManager.addSession(session, SessionManager.AUTH_ID, authNum);
    }
}
