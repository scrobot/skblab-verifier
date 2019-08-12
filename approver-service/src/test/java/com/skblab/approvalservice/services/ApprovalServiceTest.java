package com.skblab.approvalservice.services;

import com.skblab.approvalservice.models.LeadTask;
import com.skblab.approvalservice.repositories.LeadQueueRepository;
import com.skblab.protoapi.ApproveResponse;
import lombok.val;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApprovalServiceTest {

    @Mock
    private ApprovalService service;

    private final LeadTask task = LeadTask.empty();
    private final ApproveResponse response = ApproveResponse
            .newBuilder()
            .setIsSuccess(false)
            .setRequestId(1)
            .build();

    @Before
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addTaskInQueue() {
        Mockito.when(service.addTaskInQueue(1L, "test")).thenReturn(Mono.just(1L));

        Mockito.verify(service).addTaskInQueue(1L, "test")
                .subscribe(it -> {
                    assertThat(it).isEqualTo(1L);
                });
    }

    @Test
    void getNextTask() {
        Mockito.when(service.getNextTask()).thenReturn(Flux.just(task));

        Mockito.verify(service).getNextTask()
                .subscribe(it -> {
                    assertThat(it.getId()).isEqualTo(-1);
                    assertThat(it.getRequestId()).isEqualTo(-1);
                    assertThat(it.getLogin()).isNull();
                    assertThat(it.isHandled()).isFalse();
                    assertThat(it.isApproved()).isFalse();
                });
    }

    @Test
    void handleTask() {
        Mockito.when(service.handleTask(task)).thenReturn(Flux.just(response));

        Mockito.verify(service).handleTask(task)
                .subscribe(it -> {
                   assertThat(it.getRequestId()).isEqualTo(1L);
                   assertThat(it.getIsSuccess()).isFalse();
                });
    }
}