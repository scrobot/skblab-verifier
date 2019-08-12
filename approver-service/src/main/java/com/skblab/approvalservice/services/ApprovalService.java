package com.skblab.approvalservice.services;

import com.skblab.approvalservice.models.LeadTask;
import com.skblab.protoapi.ApproveResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
public interface ApprovalService {

    /**
     * Добавляет задачу в очередь
     *
     * @param requestId уникальный идентификатор запроса
     * @param login логин, который проходит проверку одобрения(на наличие его в черном списке например)
     *
     * @return correlationId в виде Long
     * */
    Mono<Long> addTaskInQueue(Long requestId, String login);

    /**
     * Получает следующую задачу из очереди
     *
     * @return реактивный стрим-Publisher с задачей
     * */
    Flux<LeadTask> getNextTask();

    /**
     * Обработчик задачи
     *
     * @param leadTask задача, требующая обработки
     *
     * @return реактивный стрим-Publisher c protobuff-моделью ответа
     * */
    Flux<ApproveResponse> handleTask(LeadTask leadTask);

}
