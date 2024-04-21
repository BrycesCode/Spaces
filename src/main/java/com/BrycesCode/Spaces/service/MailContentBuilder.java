package com.BrycesCode.Spaces.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private TemplateEngine _templateEngine;

    public MailContentBuilder(TemplateEngine templateEngine) {
        _templateEngine = templateEngine;
    }

    String build(String message){
        Context context = new Context();
        context.setVariable("message", message);
        return _templateEngine.process("mailTemplate", context);

    }
}
