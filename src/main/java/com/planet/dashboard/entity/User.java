package com.planet.dashboard.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "TB_USER")
public class User extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "user_seq")
    private Long seq;

    private Integer id;

    private String email;

    private String nickName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    private List<Board> boards = new ArrayList<>();

    private static UserBuilder createUser(String encodedPw , String email){
        return User.builder()
                .email(email)
                .password(encodedPw);
    }

    public static User createAdminUser(String encodedPw , String email){
        return createUser(encodedPw,email)
                .role(Role.ROLE_ADMIN)
                .build();
    }

    public static User createNormalUser(String encodedPw , String email){
        return createUser(encodedPw,email)
                .role(Role.ROLE_USER)
                .build();
    }

    @Builder
    public User(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, String deletedBy, String email, String password, String nickName ,List<Board> boards , Role role) {
        super(createdAt, updatedAt, deletedAt, deletedBy);
        this.email = email;
        this.password = password;
        this.boards = boards;
        this.nickName = nickName;
    }


}
