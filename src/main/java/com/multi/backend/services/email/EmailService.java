package com.multi.backend.services.email;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.multi.backend.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import io.jsonwebtoken.io.IOException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmailNewUserConnexionInfos(User user, String password) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Map<String, Object> props = new HashMap<String, Object>();
        props.put("firstName", user.getFirstName());
        props.put("lastName", user.getLastName());
        props.put("username", user.getUsername());
        props.put("password", password);
        Context context = new Context();
        context.setVariables(props);
        String html = templateEngine.process("new-user-password-email", context);
        helper.setTo(user.getEmail());
        helper.setSubject("Information de connexion de votre nouveau compte utilisateur");
        helper.setText(html, true);

        emailSender.send(message);
    }

}