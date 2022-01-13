package com.planet.dashboard.entity;

import com.planet.dashboard.controller.request.dto.RegisterForm;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class User extends BaseEntity {

    @Id
    private String email;

    private String password;

    public static User createUser(RegisterForm registerForm , String email){
        return User.builder()
                .email(email)
                .password(registerForm.getFirstPw())
                .build();
    }

    @OneToMany
    private List<Board> boards = new ArrayList<>();


}
