package com.skblab.approvalservice.services;

import com.skblab.approvalservice.models.LeadTask;
import com.skblab.approvalservice.repositories.LeadQueueRepository;
import com.skblab.protoapi.ApproveResponse;
import lombok.val;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {

    private final Logger log = LoggerFactory.getLogger(ApprovalServiceImpl.class);

    @Autowired
    private LeadQueueRepository repository;

    @Override
    public Mono<Long> addTaskInQueue(Long requestId, String login) {
        log.debug("incoming task: requestId -> " + requestId + "; login -> " + login );

        LeadTask leadTask = repository.save(new LeadTask(requestId, login));

        return Mono.just(leadTask)
                .map(LeadTask::getId);
    }

    @Override
    public Flux<LeadTask> getNextTask() {
        LeadTask task = repository.findFirstByHandledFalse();
        return task == null ? Flux.just(LeadTask.empty()) : Flux.just(task);
    }

    @Override
    public Flux<ApproveResponse> handleTask(LeadTask leadTask) {
        val isPass = leadTask.getLogin().contains("test");

        var task = repository.findFirstByRequestId(leadTask.getRequestId());
        task.setHandled(true);
        task.setApproved(isPass);
        task = repository.save(task);

        return Flux
                .just(task)
                .map(it -> ApproveResponse
                    .newBuilder()
                    .setIsSuccess(it.isApproved())
                    .setRequestId(it.getRequestId())
                    .build()
                );
    }

}
