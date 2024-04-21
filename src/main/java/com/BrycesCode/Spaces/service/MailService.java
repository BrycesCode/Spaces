package com.BrycesCode.Spaces.service;


import com.BrycesCode.Spaces.exceptions.SpacesException;
import com.BrycesCode.Spaces.model.NotificationEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    private final MailContentBuilder _mailContentBuilder;
    private final JavaMailSender _javaMailSender;

    public MailService(MailContentBuilder mailContentBuilder, JavaMailSender javaMailSender) {
        _mailContentBuilder = mailContentBuilder;
        _javaMailSender = javaMailSender;
    }

    public void sendMail(NotificationEmail notificationEmail){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("Space@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(_mailContentBuilder.build(notificationEmail.getBody()));
        };
        try{
            _javaMailSender.send(messagePreparator);
            log.info("Activation email sent");
        } catch (MailException e){
            throw new SpacesException("Exception occurred when sending mail to " + notificationEmail.getRecipient());
        }
    }
}
