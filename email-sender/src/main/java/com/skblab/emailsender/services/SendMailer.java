package com.skblab.emailsender.services;

import java.util.concurrent.TimeoutException;

/**
 * @author Alex Scrobot
 */
public interface SendMailer {
    void sendMail (String address, String topic, String text) throws TimeoutException;
}
