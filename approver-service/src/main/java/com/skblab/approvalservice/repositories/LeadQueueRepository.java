package com.skblab.approvalservice.repositories;

import com.skblab.approvalservice.models.LeadTask;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Scrobot
 */
public interface LeadQueueRepository extends CrudRepository<LeadTask, Long> {

    LeadTask findFirstByHandledTrueAndHandledFalse();
    LeadTask findFirstByRequestId(long reqId);

}
