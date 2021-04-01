package com.multi.backend.services.email;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailConfig {
    @Bean
    public JavaMailSender javamailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.google.com");
        mailSender.setPort(587);
        mailSender.setUsername("mamine.thiam@univ-thies.sn");
        mailSender.setPassword("154211Th");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.trasport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;

    }

}
