package com.planet.dashboard.service;

import com.planet.dashboard.controller.request.dto.LoginForm;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    String login(HttpServletRequest request, LoginForm form , Model model);

}
