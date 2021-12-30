package com.planet.dashboard.auth;

public interface EmailService {

    void sendSimpleMessage(String to,String title, String text);

    String sendTemplateMessage(String to);
}
