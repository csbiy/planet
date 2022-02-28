package com.planet.dashboard.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.PropertyResolver;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class EmailServiceImplTest {

    private EmailService emailService;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    PropertyResolver propertyResolver;

    @BeforeEach
    public void setUp(){
        EmailSender mailSender = new EmailSender(propertyResolver);
        MailTemplate mailTemplate = new MailTemplate(propertyResolver);
        emailService = new EmailServiceImpl(mailSender,mailTemplate,javaMailSender);

    }

    @Test
    @DisplayName("잘못된 이메일로 요청시 이메일이 전송되지 않습니다.")
    void sendMessage() {
        String invalidMailAddress = UUID.randomUUID().toString();
        assertThrows(MailSendException.class,()->emailService.sendSimpleMessage(invalidMailAddress,"testTile","testTxt"));
    }


}