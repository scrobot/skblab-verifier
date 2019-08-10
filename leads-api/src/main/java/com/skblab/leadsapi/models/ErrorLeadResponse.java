package com.skblab.leadsapi.models;

import java.util.List;

/**
 * @author Alex Scrobot
 */
public class ErrorLeadResponse {

    public final List<String> errors;

    public ErrorLeadResponse(List<String> errors) {
        this.errors = errors;
    }

}
