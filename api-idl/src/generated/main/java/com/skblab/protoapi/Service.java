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
      "e/protobuf/empty.proto\032\rmetrics.proto2P\n" +
      "\rMetricService\022?\n\004send\022\035.com.skblab.prot" +
      "o.MetricEvent\032\026.google.protobuf.Empty\"\000B" +
      "\027\n\023com.skblab.protoapiP\001b\006proto3"
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
        }, assigner);
    com.google.protobuf.EmptyProto.getDescriptor();
    com.skblab.protoapi.Metrics.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
