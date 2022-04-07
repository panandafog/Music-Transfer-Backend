package com.panandafog.mt_server.account.verification;

import com.panandafog.mt_server.entity.AppUser;
import com.panandafog.mt_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PasswordResetListener implements ApplicationListener<OnPasswordResetEvent> {

    @Autowired
    private UserService service;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnPasswordResetEvent event) {
        this.sendPasswordResetEmail(event);
    }

    private void sendPasswordResetEmail(OnPasswordResetEvent event) {
        System.out.println("Registration confirmation initiated");
        AppUser user = event.getUser();
        System.out.println("user to confirm: " + user.getEmail());
        String token = UUID.randomUUID().toString();
        service.createPasswordResetToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Password reset";
        String message = "Use this token to reset password:";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " " + token);
        System.out.println("Sending email to " + user.getEmail());
        mailSender.send(email);
        System.out.println("Email sent");
    }
}
