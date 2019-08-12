package com.skblab.metrics;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MetricsApplication.class}, webEnvironment = RANDOM_PORT)
class MetricsApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

// TODO: разкоментить тест, когда будет актуальна проверка актуатора
    /*@Test
    void actuator() {
        ResponseEntity<String> response = restTemplate.getForEntity("/actuator", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }*/

}