package com.planet.dashboard.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.SendFailedException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

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

        String authNum;
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderInfo.getId());
            message.setTo(to);
            message.setSubject(templateInfo.getTemplateMailTitle());
            authNum = generateAuthNum(8);
            message.setText(templateInfo.getTemplateMailContent() + setEmailParam("인증번호",authNum));
            mailSender.send(message);
        }catch (Exception e){
            log.info(e.getMessage());
            throw e;
        }
        return authNum;
    }

    private String setEmailParam(String key , String value) {
        return  System.lineSeparator() + " - " + key  + " : " + value ;
    }

    private String generateAuthNum(int length) {
        return UUID.randomUUID().toString().substring(0,length);
    }



}
