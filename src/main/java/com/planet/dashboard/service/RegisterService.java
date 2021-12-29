package com.planet.dashboard.service;

import com.planet.dashboard.dto.RegisterForm;
import com.planet.dashboard.entity.User;
import com.planet.dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;

       public String register(RegisterForm registerForm, Model model){

           if(isExistMember(registerForm)){
               model.addAttribute("existMember",true);
               return "register";
           }
           userRepository.save(User.createUser(registerForm));
           return "index";
       }

       private boolean isExistMember(RegisterForm registerForm){
           return userRepository.findById(registerForm.getEmail()).isPresent();

       }


}
