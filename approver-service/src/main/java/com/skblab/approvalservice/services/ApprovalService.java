package com.skblab.approvalservice.services;

import com.skblab.approvalservice.models.LeadTask;
import com.skblab.protoapi.ApproveResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
public interface ApprovalService {

    Mono<Long> addTaskInQueue(Long requestId, String login);
    Flux<LeadTask> getNextTask();
    Flux<ApproveResponse> handleTask(LeadTask leadTask);

}
