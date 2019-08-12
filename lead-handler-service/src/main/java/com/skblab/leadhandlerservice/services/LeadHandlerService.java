package com.skblab.leadhandlerservice.services;

import com.google.protobuf.Empty;
import com.skblab.leadhandlerservice.services.messaging.MessagingService;
import com.skblab.leadhandlerservice.services.registration.LeadRegistrationService;
import com.skblab.leadhandlerservice.services.registration.LeadRegistrationServiceImpl;
import com.skblab.protoapi.LeadHandleRequest;
import com.skblab.protoapi.LeadHandleResponse;
import com.skblab.protoapi.ReactorLeadRegistrationServiceGrpc;
import org.checkerframework.checker.units.qual.A;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @author Alex Scrobot
 */
@GRpcService
public class LeadHandlerService extends ReactorLeadRegistrationServiceGrpc.LeadRegistrationServiceImplBase {

    private final Logger log = LoggerFactory.getLogger(LeadHandlerService.class);

    @Autowired
    private LeadRegistrationService registrationService;

    @Autowired
    private MessagingService messagingService;

    @Override
    public Mono<LeadHandleResponse> send(Mono<LeadHandleRequest> request) {
        return request
                .map(r -> registrationService.registerLead(r))
                .log()
                .metrics()
                .doOnNext(lead -> messagingService.send(lead.getRequestId(), lead.login).subscribe())
                .doOnError(throwable -> log.error(throwable.getLocalizedMessage(), throwable))
                .flatMap(lead -> Mono.just(
                        LeadHandleResponse
                                .newBuilder()
                                .setEmail(lead.email)
                                .setRequestId(lead.getRequestId())
                                .build()
                ));
    }

    @PostConstruct
    private void startListeningReceivingMessages() {
        messagingService.subscribeOnInboxMessages();
    }
}
