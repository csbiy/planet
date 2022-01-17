package com.planet.dashboard.controller.request.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginForm {

    @NotBlank
    private  String email;

    @NotBlank
    @Length(max = 2000,message = "hi~~~")
    private  String password;


}
