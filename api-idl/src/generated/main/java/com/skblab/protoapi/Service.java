// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service.proto

package com.skblab.protoapi;

public final class Service {
  private Service() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rservice.proto\022\020com.skblab.proto\032\033googl" +
      "e/protobuf/empty.proto\032\rmetrics.proto\032\nl" +
      "ead.proto\032\013email.proto\032\016approver.proto2P" +
      "\n\rMetricService\022?\n\004send\022\035.com.skblab.pro" +
      "to.MetricEvent\032\026.google.protobuf.Empty\"\000" +
      "2n\n\027LeadRegistrationService\022S\n\004send\022#.co" +
      "m.skblab.proto.LeadHandleRequest\032$.com.s" +
      "kblab.proto.LeadHandleResponse\"\0002[\n\022Emai" +
      "lSenderService\022E\n\nsendLetter\022\035.com.skbla" +
      "b.proto.EmailLetter\032\026.google.protobuf.Em",
      "pty\"\0002\311\001\n\025ApproveRequestService\022W\n\rinitA" +
      "pproving\022 .com.skblab.proto.ApproveReque" +
      "st\032\".com.skblab.proto.ApproveRequestId\"\000" +
      "\022W\n\026receiveApprovingStatus\022\026.google.prot" +
      "obuf.Empty\032!.com.skblab.proto.ApproveRes" +
      "ponse\"\0000\001B\027\n\023com.skblab.protoapiP\001b\006prot" +
      "o3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.EmptyProto.getDescriptor(),
          com.skblab.protoapi.Metrics.getDescriptor(),
          com.skblab.protoapi.Lead.getDescriptor(),
          com.skblab.protoapi.Email.getDescriptor(),
          com.skblab.protoapi.Approver.getDescriptor(),
        }, assigner);
    com.google.protobuf.EmptyProto.getDescriptor();
    com.skblab.protoapi.Metrics.getDescriptor();
    com.skblab.protoapi.Lead.getDescriptor();
    com.skblab.protoapi.Email.getDescriptor();
    com.skblab.protoapi.Approver.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
