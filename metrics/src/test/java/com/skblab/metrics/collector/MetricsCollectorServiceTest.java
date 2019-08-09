package com.skblab.metrics.collector;

import com.google.protobuf.Empty;
import com.skblab.protoapi.MetricEvent;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MetricsCollectorServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void send() {
        new MetricsCollectorService().send(MetricEvent.getDefaultInstance(), new StreamObserver<Empty>() {
            @Override
            public void onNext(Empty value) {
                assertEquals(value, Empty.getDefaultInstance());
            }

            @Override
            public void onError(Throwable t) {
                assertNull(t);
            }

            @Override
            public void onCompleted() {
                assertTrue(true);
            }
        });
    }

}