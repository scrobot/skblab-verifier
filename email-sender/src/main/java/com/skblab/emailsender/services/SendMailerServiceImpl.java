package com.skblab.emailsender.services;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Alex Scrobot
 */
@Service
public class SendMailerServiceImpl implements SendMailerService {

    @Autowired
    private JavaMailSender emailSender;

    private final Logger log = LoggerFactory.getLogger(SendMailerServiceImpl.class);

    @Override
    public void sendMail(String address, String topic, String text) throws TimeoutException {
        if (shouldThrowTimeout()) {
            sleep();

            throw new TimeoutException("Timeout!");
        }

        if (shouldSleep()) {
            sleep();
        }

        // ok.
        log.info(String.format("Message sent to %s, body %s.", address, text));

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject(topic);
        simpleMailMessage.setTo(address);
        simpleMailMessage.setText(text);

        emailSender.send(simpleMailMessage);
    }

    @SneakyThrows
    private static void sleep() {
        Thread.sleep(TimeUnit.MINUTES.toMillis(1));
    }

    private static boolean shouldSleep() {
        return new Random().nextInt(10) == 1;
    }

    private static boolean shouldThrowTimeout() {
        return new Random().nextInt(10) == 1;
    }

}
