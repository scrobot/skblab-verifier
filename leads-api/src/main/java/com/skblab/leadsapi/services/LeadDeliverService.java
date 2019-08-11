package com.skblab.leadsapi.services;

import com.skblab.leadsapi.models.LeadRequest;
import com.skblab.leadsapi.models.LeadResponse;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
public interface LeadDeliverService {

    public Mono<LeadResponse> sendMessage(LeadRequest request);

}
