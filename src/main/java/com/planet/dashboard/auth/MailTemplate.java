package com.planet.dashboard.auth;

import lombok.Getter;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MailTemplate {

    private final String templateMailTitle;

    private final String templateMailContent;

    public MailTemplate(PropertyResolver resolver) {
        this.templateMailTitle = resolver.getProperty("smtp.template.title");
        this.templateMailContent = resolver.getProperty("smtp.template.content");
    }
}
