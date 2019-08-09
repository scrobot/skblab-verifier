package com.skblab.leadsapi.controllers;

import com.skblab.leadsapi.models.LeadRequestBody;
import com.skblab.leadsapi.models.LeadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
@RestController
public class LeadController {

    @PostMapping("register")
    public Mono<LeadResponse> register(@RequestBody LeadRequestBody body) {
        return Mono.empty();
    }

}
