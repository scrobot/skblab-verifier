package com.skblab.emailsender.services;

import java.util.concurrent.TimeoutException;

/**
 * @author Alex Scrobot
 */
public interface SendMailerService {

    /**
     * Метод отправки письма получателю.
     *
     * @param address - адрес получателя
     * @param topic - тема письма
     * @param text - тело письма
     *
     * @throws TimeoutException может выбрасывать исключение по TimeOut'у
     * */
    void sendMail (String address, String topic, String text) throws TimeoutException;


}
