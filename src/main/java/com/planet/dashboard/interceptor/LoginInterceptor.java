package com.planet.dashboard.interceptor;

import com.planet.dashboard.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(isMember(request)){
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }

    private boolean isMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return !(session == null || session.getAttribute(SessionManager.SESSION_ID) == null);
    }


}
