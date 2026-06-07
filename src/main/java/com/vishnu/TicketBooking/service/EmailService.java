package com.vishnu.TicketBooking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger logger =
            LoggerFactory.getLogger(
                    EmailService.class
            );

    private final JavaMailSender mailSender;

    public EmailService(
            JavaMailSender mailSender
    ) {
        this.mailSender = mailSender;
    }

//    @Async
    public void sendEmail(
            String to,
            String subject,
            String body
    ) {
        System.out.println("EMAIL METHOD CALLED");

        try {
            System.out.println("EMAIL METHOD CALLED");

            logger.info("Sending email...");

            SimpleMailMessage message =
                    new SimpleMailMessage();
            message.setFrom("newidagent@gmail.com");

            message.setTo(to);

            message.setSubject(subject);

            message.setText(body);
            System.out.println("BEFORE SEND");
            mailSender.send(message);
            System.out.println("AFTER SEND");
            logger.info("Email sent successfully");

        } catch (Exception e) {
            System.out.println("EMAIL ERROR OCCURRED");
            logger.error("Email sending failed", e);
            e.printStackTrace();
        }
    }
}