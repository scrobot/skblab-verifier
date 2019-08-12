package com.skblab.approvalservice.repositories;

import com.skblab.approvalservice.models.LeadTask;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Scrobot
 *
 * Костыльно-колхозная реализация очереди
 */
public interface LeadQueueRepository extends CrudRepository<LeadTask, Long> {

    /**
     * Запрашивает задачу подходящие под условие "не обработана"
     *
     * @return LeadTask для прохождения одобрения
     * */
    LeadTask findFirstByHandledFalse();

    /**
     * Запрашивает задачу по ее RequestId
     *
     * @return модель LeadTask
     * */
    LeadTask findFirstByRequestId(long reqId);

}
