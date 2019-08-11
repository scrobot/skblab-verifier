package com.skblab.leadsapi.configuration;

import com.skblab.protoapi.ReactorLeadRegistrationServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Scrobot
 */
@Configuration
public class GrpcConfiguration {

    private Channel configureChannel(int port) {
        val channelBuilder = ManagedChannelBuilder.forAddress("localhost", port);
        channelBuilder.usePlaintext();

        return channelBuilder.build();
    }

    @Bean
    public ReactorLeadRegistrationServiceGrpc.ReactorLeadRegistrationServiceStub stub() {
        return ReactorLeadRegistrationServiceGrpc.newReactorStub(configureChannel(9092));
    }

}
