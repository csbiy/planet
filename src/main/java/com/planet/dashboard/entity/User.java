package com.planet.dashboard.entity;

import com.planet.dashboard.controller.request.dto.RegisterForm;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class User extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    private List<Board> boards = new ArrayList<>();

    public static User createUser(RegisterForm registerForm , String email){
        return User.builder()
                .email(email)
                .password(registerForm.getFirstPw())
                .build();
    }



}
