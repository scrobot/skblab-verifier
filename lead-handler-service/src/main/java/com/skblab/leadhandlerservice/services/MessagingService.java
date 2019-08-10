package com.skblab.leadhandlerservice.services;

import com.skblab.protoapi.LeadHandleRequest;
import com.skblab.protoapi.LeadHandleResponse;
import com.skblab.protoapi.ReactorLeadRegistrationServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
@GRpcService
public class MessagingService extends ReactorLeadRegistrationServiceGrpc.LeadRegistrationServiceImplBase {

    @Override
    public Mono<LeadHandleResponse> send(Mono<LeadHandleRequest> request) {
        return request.flatMap(r -> Mono.just(
                LeadHandleResponse
                        .newBuilder()
                        .setEmail(r.getEmail())
                        .setRequestId(100L)
                        .build()
        ));
    }
}
