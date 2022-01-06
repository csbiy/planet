package com.planet.dashboard.entity;

import com.planet.dashboard.controller.request.dto.EmailForm;
import com.planet.dashboard.controller.request.dto.RegisterForm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String email;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public static User createUser(RegisterForm registerForm , String email){
        return User.builder()
                .email(email)
                .password(registerForm.getFirstPw())
                .build();
    }


}
