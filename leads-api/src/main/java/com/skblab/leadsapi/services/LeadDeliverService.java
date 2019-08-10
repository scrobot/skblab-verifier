package com.skblab.leadsapi.services;

import com.skblab.leadsapi.models.LeadResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
@Service
public class LeadDeliverService {

    public Mono<LeadResponse> sendMessage() {
        return Mono.just(new LeadResponse());
    }

}
