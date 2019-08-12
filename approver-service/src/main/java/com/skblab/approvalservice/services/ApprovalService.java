package com.skblab.approvalservice.services;

import com.skblab.approvalservice.models.LeadTask;
import com.skblab.protoapi.ApproveResponse;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
public interface ApprovalService {

    Mono<Long> addTaskInQueue(Long requestId, String login);
    Mono<LeadTask> getNextTask();
    Mono<ApproveResponse> handleTask(LeadTask leadTask);

}
