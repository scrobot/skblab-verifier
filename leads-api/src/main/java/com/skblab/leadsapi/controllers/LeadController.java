package com.skblab.leadsapi.controllers;

import com.skblab.leadsapi.models.ErrorLeadResponse;
import com.skblab.leadsapi.models.LeadRequest;
import com.skblab.leadsapi.models.LeadResponse;
import com.skblab.leadsapi.services.LeadDeliverService;
import com.skblab.leadsapi.services.LeadDeliverServiceImpl;
import com.skblab.leadsapi.validation.LeadRegisterValidator;
import com.skblab.leadsapi.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author Alex Scrobot
 */
@RestController
public class LeadController {

    private final Logger logger = LoggerFactory.getLogger(LeadController.class);

    private final LeadRegisterValidator validator = new LeadRegisterValidator();

    @Autowired
    private LeadDeliverService service;

    @PostMapping("/api/register")
    public Mono register(@RequestBody LeadRequest body) {
        return Mono.just(body)
                .log()
                .flatMap(lead -> {
                    if (validator.hasErrors(lead)) {
                        return Mono.error(new ValidationException("lead has errors"));
                    }

                    return service.sendMessage(body);
                })
                .map(i -> new ResponseEntity<LeadResponse>(i, HttpStatus.OK))
                .doOnError(throwable -> logger.error(throwable.getLocalizedMessage(), throwable))
                .onErrorReturn(handleError(body));
    }

    private ResponseEntity handleError(@RequestBody LeadRequest body) {
        return new ResponseEntity<ErrorLeadResponse>(
                new ErrorLeadResponse(
                        validator.getErrorMessages(body)
                ),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

}
