package com.planet.dashboard.config;

import com.planet.dashboard.auth.EmailSender;
import com.planet.dashboard.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final EmailSender mailSender;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/index","/","/login","/register/**","/css/**","/js/**","/vendor/**","/swagger-ui")
//                .order(1);
    }

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages_error","classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl config = new JavaMailSenderImpl();
        config.setHost(mailSender.getEmailPlatform().getHost());
        config.setPort(mailSender.getEmailPlatform().getPort());
        config.setPassword(mailSender.getPassword());
        config.setUsername(mailSender.getId());
        Properties props = config.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", mailSender.getEmailPlatform().getHost());
        props.put("mail.debug", "true");
        return config;
    }
}
