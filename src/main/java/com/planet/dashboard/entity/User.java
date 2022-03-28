package com.planet.dashboard.entity;

import com.planet.dashboard.controller.request.dto.RegisterForm;
import lombok.*;

import javax.persistence.*;
<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> feat-01
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "user_seq")
    private Long seq;
=======
    private Integer id;

>>>>>>> feat-01
    private String email;

    private String nickName;

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

<<<<<<< HEAD
    @Builder
    public User(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String deletedBy, String email, String password, String nickName ,List<Board> boards) {
        super(createdAt, updatedAt, deletedAt, deletedBy);
        this.email = email;
        this.password = password;
        this.boards = boards;
        this.nickName = nickName;
    }

    @OneToMany
    private List<Board> boards;
=======
>>>>>>> feat-01


}
