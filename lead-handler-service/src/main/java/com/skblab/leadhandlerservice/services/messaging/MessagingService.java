package com.skblab.leadhandlerservice.services.messaging;

import com.skblab.protoapi.ApproveRequestId;
import com.skblab.protoapi.ApproveResponse;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeoutException;

/**
 * @author Alex Scrobot
 */
public interface MessagingService {

    /**
     * Отправка сообщения в шину. Операция асинхронная, ответа данный запрос не имеет
     *
     * @param requestId идентификатор сообщения для отправки.
     * @param login проверяем логин на безопасность, например: не находится ли он в черном списке.
     *
     * @return идентификатор отправленного сообщения (correlationId)
     */
    Mono<ApproveRequestId> send(long requestId, String login);

    /**
     * Приемник входящих сообщений от сервиса проверки.
     * Реализует подписку на однонаправленный стриминг.
     *
     * Редко, но может кинуть исключение по таймауту.
     *
     * @param response ответ от сервиса проверки
     */
    void receive(ApproveResponse response) throws TimeoutException;

    /**
     * Активирует подписку на входящие сообщения
     *
     * */
    void subscribeOnInboxMessages();
}
