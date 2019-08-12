package com.skblab.approvalservice.services;

import com.google.protobuf.Empty;
import com.skblab.approvalservice.models.LeadTask;
import com.skblab.protoapi.ApproveRequest;
import com.skblab.protoapi.ApproveRequestId;
import com.skblab.protoapi.ApproveResponse;
import com.skblab.protoapi.ReactorApproveRequestServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

/**
 * @author Alex Scrobot
 */
@GRpcService
public class ApproverRequestService extends ReactorApproveRequestServiceGrpc.ApproveRequestServiceImplBase {

    private final Logger log = LoggerFactory.getLogger(ApproverRequestService.class);

    @Autowired
    private ApprovalService approvalService;

    @Override
    public Mono<ApproveRequestId> initApproving(Mono<ApproveRequest> request) {
        return request
                .flatMap(r -> approvalService.addTaskInQueue(r.getRequestId(), r.getLogin()))
                .map(correlationId -> ApproveRequestId
                        .newBuilder()
                        .setCorrelationId(correlationId)
                        .build()
                );
    }

    @Override
    public Flux<ApproveResponse> receiveApprovingStatus(Mono<Empty> request) {
        return Flux.interval(Duration.ofMillis(500))
                .subscribeOn(Schedulers.elastic())
                .flatMap(i -> approvalService.getNextTask())
                .filter(it -> it.getRequestId() > 0)
                .flatMap(task -> approvalService.handleTask(task));
    }
}
