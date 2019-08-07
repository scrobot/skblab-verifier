package com.skblab.protoapi;

import static com.skblab.protoapi.MetricServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: service.proto")
public final class ReactorMetricServiceGrpc {
    private ReactorMetricServiceGrpc() {}

    public static ReactorMetricServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorMetricServiceStub(channel);
    }

    public static final class ReactorMetricServiceStub extends io.grpc.stub.AbstractStub<ReactorMetricServiceStub> {
        private MetricServiceGrpc.MetricServiceStub delegateStub;

        private ReactorMetricServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = MetricServiceGrpc.newStub(channel);
        }

        private ReactorMetricServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = MetricServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorMetricServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorMetricServiceStub(channel, callOptions);
        }

        public reactor.core.publisher.Mono<com.google.protobuf.Empty> send(reactor.core.publisher.Mono<com.skblab.protoapi.MetricEvent> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::send);
        }

        public reactor.core.publisher.Mono<com.google.protobuf.Empty> send(com.skblab.protoapi.MetricEvent reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::send);
        }

    }

    public static abstract class MetricServiceImplBase implements io.grpc.BindableService {

        public reactor.core.publisher.Mono<com.google.protobuf.Empty> send(reactor.core.publisher.Mono<com.skblab.protoapi.MetricEvent> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            com.skblab.protoapi.MetricServiceGrpc.getSendMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            com.skblab.protoapi.MetricEvent,
                                            com.google.protobuf.Empty>(
                                            this, METHODID_SEND)))
                    .build();
        }
    }

    private static final int METHODID_SEND = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final MetricServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(MetricServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SEND:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((com.skblab.protoapi.MetricEvent) request,
                            (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver,
                            serviceImpl::send);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
