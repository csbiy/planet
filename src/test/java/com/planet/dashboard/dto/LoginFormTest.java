package com.planet.dashboard.dto;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoginFormTest {

    @Test
    public void testBeanValidation(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(" ");

        Set<ConstraintViolation<LoginForm>> violations = validator.validate(loginForm);
        for (ConstraintViolation<LoginForm> violation : violations) {
            System.out.println("violation = " + violation);
            System.out.println("violation.getMessage() = " + violation.getMessage());
        }
    }

}