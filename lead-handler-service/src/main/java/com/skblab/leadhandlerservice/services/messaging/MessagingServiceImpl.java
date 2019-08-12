package com.skblab.leadhandlerservice.services.messaging;

import com.google.protobuf.Empty;
import com.skblab.leadhandlerservice.models.EmailLetterWrapper;
import com.skblab.leadhandlerservice.repositories.LeadRepository;
import com.skblab.protoapi.*;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * @author Alex Scrobot
 */
@Service
public class MessagingServiceImpl implements MessagingService {

    private final Logger log = LoggerFactory.getLogger(MessagingServiceImpl.class);

    @Autowired
    private LeadRepository repository;

    @Autowired
    private ReactorEmailSenderServiceGrpc.ReactorEmailSenderServiceStub emailSenderServiceStub;

    @Autowired
    private ReactorApproveRequestServiceGrpc.ReactorApproveRequestServiceStub approveRequestServiceStub;

    @Override
    public Mono<ApproveRequestId> send(long requestId, String login) {
        return approveRequestServiceStub.initApproving(
                ApproveRequest
                        .newBuilder()
                        .setRequestId(requestId)
                        .setLogin(login)
                        .build()
        );
    }

    @Override
    public void receive(ApproveResponse response) {
        try {
            val lead = repository.findById(response.getRequestId())
                    .orElseThrow((Supplier<Throwable>) () ->
                            new Exception("Lead #" + response.getRequestId() + " was not found")
                    );
            emailSenderServiceStub.sendLetter(
                    Mono.just(lead)
                            .map(l -> {
                                if (response.getIsSuccess()) {
                                    return new EmailLetterWrapper(
                                            "You are verified!",
                                            l.email,
                                            "Your request #" + response.getRequestId() + " was passed verification"
                                    );
                                } else {
                                    return new EmailLetterWrapper(
                                            "You are not verified!",
                                            l.email,
                                            "Your request #" + response.getRequestId() + " was not passed verification"
                                    );
                                }
                            })
                            .map(wrapper -> EmailLetter
                                    .newBuilder()
                                    .setTopic(wrapper.getTopic())
                                    .setRecipientAddress(wrapper.getAddress())
                                    .setText(wrapper.getText())
                                    .build()
                            )
                    )
                    .retryBackoff(5, Duration.ofSeconds(1))
                    .doOnError(throwable -> log.error(throwable.getLocalizedMessage(), throwable))
                    .subscribe();
        } catch (Throwable throwable) {
            log.error(throwable.getLocalizedMessage(), throwable);
        }
    }

    @Override
    public void subscribeOnInboxMessages() {
        approveRequestServiceStub.receiveApprovingStatus(Empty.getDefaultInstance())
                .retryBackoff(5, Duration.ofSeconds(1))
                .doOnError(throwable -> log.error(throwable.getLocalizedMessage(), throwable))
                .subscribe(this::receive);
    }


}
