package com.skblab.approvalservice.services;

import com.google.protobuf.Empty;
import com.skblab.protoapi.ApproveRequest;
import com.skblab.protoapi.ApproveRequestId;
import com.skblab.protoapi.ApproveResponse;
import com.skblab.protoapi.ReactorApproveRequestServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author Alex Scrobot
 */
@GRpcService
public class ApproverRequestService extends ReactorApproveRequestServiceGrpc.ApproveRequestServiceImplBase {

    @Autowired
    private ApprovalService approvalServiceImpl;

    @Override
    public Mono<ApproveRequestId> initApproving(Mono<ApproveRequest> request) {
        return request
                .flatMap(r -> approvalServiceImpl.addTaskInQueue(r.getRequestId(), r.getLogin()))
                .map(correlationId -> ApproveRequestId
                        .newBuilder()
                        .setCorrelationId(correlationId)
                        .build()
                );
    }

    @Override
    public Flux<ApproveResponse> receiveApprovingStatus(Mono<Empty> request) {
        return Flux.interval(Duration.ofMillis(500))
                .flatMap(i -> approvalServiceImpl.getNextTask().flux())
                .distinctUntilChanged()
                .flatMap(task -> approvalServiceImpl.handleTask(task));

    }
}
