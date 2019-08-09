package com.skblab.metrics.collector;

import com.google.protobuf.Empty;
import com.skblab.protoapi.MetricEvent;
import com.skblab.protoapi.MetricServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alex Scrobot
 */
@GRpcService
public class MetricsCollectorService extends MetricServiceGrpc.MetricServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(MetricsCollectorService.class);

    @Override
    public void send(MetricEvent request, StreamObserver<Empty> responseObserver) {
        logger.debug("Incoming metric from microservice:", request.getMicroservice());
//         TODO: записывать тут кастомные метрики в influxDB
    }
}
