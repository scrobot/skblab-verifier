package com.skblab.approvalservice.services;

import com.google.protobuf.Empty;
import com.skblab.protoapi.ApproveRequest;
import com.skblab.protoapi.ApproveRequestId;
import com.skblab.protoapi.ApproveResponse;
import com.skblab.protoapi.ReactorApproveRequestServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
@GRpcService
public class ApproverRequestService extends ReactorApproveRequestServiceGrpc.ApproveRequestServiceImplBase {

    @Override
    public Mono<ApproveRequestId> initApproving(Mono<ApproveRequest> request) {
        return super.initApproving(request);
    }

    @Override
    public Flux<ApproveResponse> receiveApprovingStatus(Mono<Empty> request) {
        return super.receiveApprovingStatus(request);
    }
}
