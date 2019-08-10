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

    @Bean
    public Channel configureChannel() {
        val channelBuilder = ManagedChannelBuilder.forAddress("localhost", 9092);
        channelBuilder.usePlaintext();

        return channelBuilder.build();
    }

    @Bean
    public ReactorLeadRegistrationServiceGrpc.ReactorLeadRegistrationServiceStub stub(Channel channel) {
        return ReactorLeadRegistrationServiceGrpc.newReactorStub(channel);
    }

}
