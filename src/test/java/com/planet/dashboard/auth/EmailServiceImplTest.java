package com.planet.dashboard.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceImplTest {

    @Autowired
    EmailServiceImpl emailService;
    @Test
    void sendSimpleMessage() {
        emailService.sendTemplateMessage("qefeqwf@weqfqewfqfq.com");
    }
}