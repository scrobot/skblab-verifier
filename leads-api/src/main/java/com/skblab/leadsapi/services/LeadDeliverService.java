package com.skblab.leadsapi.services;

import com.skblab.leadsapi.models.LeadRequest;
import com.skblab.leadsapi.models.LeadResponse;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
public interface LeadDeliverService {

    /**
     * Отправка сообщения в систему обработки запросов, для ее валидации и регистрации
     *
     * @param request модель запроса регистрации заявки
     *
     * @return моно-Publisher с телом ответа
     * */
    Mono<LeadResponse> sendMessage(LeadRequest request);

}
