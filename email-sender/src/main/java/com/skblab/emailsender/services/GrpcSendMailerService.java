package com.skblab.emailsender.services;

import com.google.protobuf.Empty;
import com.skblab.protoapi.EmailLetter;
import com.skblab.protoapi.ReactorEmailSenderServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author Alex Scrobot
 */
@GRpcService
public class GrpcSendMailerService extends ReactorEmailSenderServiceGrpc.EmailSenderServiceImplBase {

    @Autowired
    private SendMailer sendMailer;

    @Override
    public Mono<Empty> sendLetter(Mono<EmailLetter> request) {
        return request.log()
                .timeout(Duration.ofSeconds(10))
                .flatMap(r ->
                        Mono.fromCallable(() -> {
                            sendMailer.sendMail(r.getRecipientAddress(), r.getTopic(), r.getText());
                            return Empty.getDefaultInstance();
                        }
                    )
                );
    }
}
