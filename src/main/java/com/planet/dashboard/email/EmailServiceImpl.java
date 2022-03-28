package com.planet.dashboard.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final int AUTH_LENGTH = 8;
    private final EmailSender senderInfo;
    private final MailTemplate templateInfo;
    private final JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String title , String text ){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderInfo.getId());
        message.setTo(to);
        message.setSubject(title);
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public String sendTemplateMessage(String to) {

        String authNum = generateAuthNum(AUTH_LENGTH);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderInfo.getId());
        message.setTo(to);
        message.setSubject(templateInfo.getTemplateMailTitle());
        message.setText(templateInfo.getTemplateMailContent() + setEmailParam("인증번호",authNum));
        mailSender.send(message);

        return authNum;
    }

    private String setEmailParam(String key , String value) {
        return  System.lineSeparator() + " - " + key  + " : " + value ;
    }

    private String generateAuthNum(int length) {
        return UUID.randomUUID().toString().substring(0,length);
    }



}
