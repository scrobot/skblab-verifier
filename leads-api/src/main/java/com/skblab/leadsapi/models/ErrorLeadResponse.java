package com.skblab.leadsapi.models;

import lombok.Data;

import java.util.List;

/**
 * @author Alex Scrobot
 */
public class ErrorLeadResponse extends LeadResponse {

    public final List<String> errors;

    public ErrorLeadResponse(List<String> errors) {
        this.errors = errors;
    }

}
