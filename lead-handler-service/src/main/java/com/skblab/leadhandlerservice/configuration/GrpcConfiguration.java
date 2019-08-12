package com.skblab.leadhandlerservice.configuration;

import com.skblab.protoapi.ReactorApproveRequestServiceGrpc;
import com.skblab.protoapi.ReactorEmailSenderServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.NettyChannelBuilder;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex Scrobot
 */
@Configuration
public class GrpcConfiguration {

    private Channel configureChannel(int port) {
        val channelBuilder = NettyChannelBuilder.forAddress("localhost", port);
        channelBuilder.usePlaintext();

        return channelBuilder.build();
    }

    @Bean
    public ReactorEmailSenderServiceGrpc.ReactorEmailSenderServiceStub emailSenderStub() {
        return ReactorEmailSenderServiceGrpc.newReactorStub(configureChannel(9093));
    }

    @Bean
    public ReactorApproveRequestServiceGrpc.ReactorApproveRequestServiceStub approveStub() {
        return ReactorApproveRequestServiceGrpc.newReactorStub(configureChannel(9094));
    }

}
