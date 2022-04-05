package com.planet.dashboard.service;

import com.planet.dashboard.SessionManager;
import com.planet.dashboard.controller.request.dto.RegisterForm;
import com.planet.dashboard.email.EmailSession;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class RegisterService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

   public User register(RegisterForm registerForm, HttpSession session){
       EmailSession emailSession = (EmailSession) SessionManager.getSession(session, SessionManager.EMAIL_AUTH);
       return userRepository.save(User.createNormalUser(passwordEncoder.encode(registerForm.getFirstPw()), emailSession.getEmail(),registerForm.getNickName()));
   }




}
