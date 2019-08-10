package com.skblab.leadsapi.services;

import com.google.protobuf.StringValue;
import com.skblab.leadsapi.models.LeadRequest;
import com.skblab.leadsapi.models.LeadResponse;
import com.skblab.protoapi.LeadHandleRequest;
import com.skblab.protoapi.ReactorLeadRegistrationServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
@Service
public class LeadDeliverService {

    @Autowired
    private ReactorLeadRegistrationServiceGrpc.ReactorLeadRegistrationServiceStub stub;

    public Mono<LeadResponse> sendMessage(LeadRequest request) {
        return stub.send(
                LeadHandleRequest
                        .newBuilder()
                        .setLogin(request.getLogin())
                        .setPassword(request.getPassword())
                        .setEmail(request.getEmail())
                        .setFullname(StringValue
                                .newBuilder()
                                .setValue(request.getFullname())
                                .build()
                        )
                        .build()
        ).map(response -> new LeadResponse(response.getRequestId(), response.getEmail()));
    }

}
