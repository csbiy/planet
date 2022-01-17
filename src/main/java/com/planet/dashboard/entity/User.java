package com.planet.dashboard.entity;

import com.planet.dashboard.controller.request.dto.RegisterForm;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
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

    @Builder
    public User(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String deletedBy, String email, String password, List<Board> boards) {
        super(createdAt, updatedAt, deletedAt, deletedBy);
        this.email = email;
        this.password = password;
        this.boards = boards;
    }

    @OneToMany
    private List<Board> boards;


}
