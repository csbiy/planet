package com.planet.dashboard.auth;

import com.planet.dashboard.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class EmailHandler {

    private final Integer sessionInvalidateDuration= 3; // minute
    private final EmailService emailServiceImpl;

    public void send(String email , HttpServletRequest request){
        String authNum = emailServiceImpl.sendTemplateMessage(email);
        HttpSession session = request.getSession();
        EmailSession emailSession = new EmailSession(authNum, email);
        SessionManager.addSession( session, SessionManager.EMAIL_AUTH  , emailSession , sessionInvalidateDuration );

    }
}
