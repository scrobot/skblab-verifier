package com.skblab.approvalservice.services;

import com.skblab.approvalservice.models.LeadTask;
import com.skblab.approvalservice.repositories.LeadQueueRepository;
import com.skblab.protoapi.ApproveResponse;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Autowired
    private LeadQueueRepository repository;

    @Override
    public Mono<Long> addTaskInQueue(Long requestId, String login) {
        LeadTask leadTask = new LeadTask(requestId, login);

        return Mono.just(repository.save(leadTask))
                .map(LeadTask::getId);
    }

    @Override
    public Mono<LeadTask> getNextTask() {
        return Mono.just(repository.findFirstByHandledTrueAndHandledFalse())
                .onErrorReturn(LeadTask.empty());
    }

    @Override
    public Mono<ApproveResponse> handleTask(LeadTask leadTask) {
        val isPass = leadTask.getLogin().contains("test");

        return Mono.just(
                ApproveResponse
                        .newBuilder()
                        .setIsSuccess(isPass)
                        .setRequestId(leadTask.getRequestId())
                        .build()
                )
                .doOnNext(response -> {
                   val task = repository.findFirstByRequestId(response.getRequestId());
                   task.setHandled(true);
                   task.setApproved(response.getIsSuccess());
                   repository.save(task);
                });
    }

}
