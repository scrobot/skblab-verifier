package com.skblab.protoapi;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.22.1)",
    comments = "Source: service.proto")
public final class MetricServiceGrpc {

  private MetricServiceGrpc() {}

  public static final String SERVICE_NAME = "com.skblab.proto.MetricService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.skblab.protoapi.MetricEvent,
      com.google.protobuf.Empty> getSendMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "send",
      requestType = com.skblab.protoapi.MetricEvent.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.skblab.protoapi.MetricEvent,
      com.google.protobuf.Empty> getSendMethod() {
    io.grpc.MethodDescriptor<com.skblab.protoapi.MetricEvent, com.google.protobuf.Empty> getSendMethod;
    if ((getSendMethod = MetricServiceGrpc.getSendMethod) == null) {
      synchronized (MetricServiceGrpc.class) {
        if ((getSendMethod = MetricServiceGrpc.getSendMethod) == null) {
          MetricServiceGrpc.getSendMethod = getSendMethod = 
              io.grpc.MethodDescriptor.<com.skblab.protoapi.MetricEvent, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.skblab.proto.MetricService", "send"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.skblab.protoapi.MetricEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new MetricServiceMethodDescriptorSupplier("send"))
                  .build();
          }
        }
     }
     return getSendMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MetricServiceStub newStub(io.grpc.Channel channel) {
    return new MetricServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MetricServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MetricServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MetricServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MetricServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MetricServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void send(com.skblab.protoapi.MetricEvent request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.skblab.protoapi.MetricEvent,
                com.google.protobuf.Empty>(
                  this, METHODID_SEND)))
          .build();
    }
  }

  /**
   */
  public static final class MetricServiceStub extends io.grpc.stub.AbstractStub<MetricServiceStub> {
    private MetricServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MetricServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MetricServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MetricServiceStub(channel, callOptions);
    }

    /**
     */
    public void send(com.skblab.protoapi.MetricEvent request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class MetricServiceBlockingStub extends io.grpc.stub.AbstractStub<MetricServiceBlockingStub> {
    private MetricServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MetricServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MetricServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MetricServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty send(com.skblab.protoapi.MetricEvent request) {
      return blockingUnaryCall(
          getChannel(), getSendMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MetricServiceFutureStub extends io.grpc.stub.AbstractStub<MetricServiceFutureStub> {
    private MetricServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MetricServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MetricServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MetricServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> send(
        com.skblab.protoapi.MetricEvent request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), request);
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
          serviceImpl.send((com.skblab.protoapi.MetricEvent) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MetricServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MetricServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.skblab.protoapi.Service.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MetricService");
    }
  }

  private static final class MetricServiceFileDescriptorSupplier
      extends MetricServiceBaseDescriptorSupplier {
    MetricServiceFileDescriptorSupplier() {}
  }

  private static final class MetricServiceMethodDescriptorSupplier
      extends MetricServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MetricServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (MetricServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MetricServiceFileDescriptorSupplier())
              .addMethod(getSendMethod())
              .build();
        }
      }
    }
    return result;
  }
}
