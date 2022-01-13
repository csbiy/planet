package com.planet.dashboard.auth;

import com.planet.dashboard.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class EmailHandler {

    private static final Integer SESSION_INVALIDATE_DURATION = 3; // minute
    private final EmailService emailService;

    public void send(String email , HttpServletRequest request){
        String authNum = emailService.sendTemplateMessage(email);
        HttpSession session = request.getSession();
        EmailSession emailSession = new EmailSession(authNum, email);
        SessionManager.addSession( session, SessionManager.EMAIL_AUTH  , emailSession , SESSION_INVALIDATE_DURATION );

    }
}
