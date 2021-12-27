package com.planet.dashboard.filter;

import com.planet.dashboard.service.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private final String[] LOGIN_FREE_PATH = {"/","/index","/login"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        try {
            if(isLoginCheckPath(httpServletRequest)){
                if(isLoginSessionNotExist(session)){
                    httpServletResponse.sendRedirect("/login");
                    return;
                }
            }
            chain.doFilter(request,response);
        } catch (ServletException e) {
            throw e;
        }

    }

    private boolean isLoginCheckPath(HttpServletRequest httpServletRequest) {
        return !(PatternMatchUtils.simpleMatch(LOGIN_FREE_PATH, httpServletRequest.getRequestURI()));
    }

    private boolean isLoginSessionNotExist(HttpSession session) {
        return (session == null || session.getAttribute(SessionManager.SESSION_ID) == null);
    }
}
