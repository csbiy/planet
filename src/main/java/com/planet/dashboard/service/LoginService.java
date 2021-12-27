package com.planet.dashboard.service;

import com.planet.dashboard.dto.LoginForm;
import com.planet.dashboard.entity.UserId;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    String login(HttpServletRequest request, LoginForm form);

    void addSession(HttpServletRequest request , UserId userId);

}
