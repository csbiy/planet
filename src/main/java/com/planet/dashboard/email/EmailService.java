package com.planet.dashboard.email;

public interface EmailService {

    void sendSimpleMessage(String to,String title, String text);

    String sendTemplateMessage(String to);
}
