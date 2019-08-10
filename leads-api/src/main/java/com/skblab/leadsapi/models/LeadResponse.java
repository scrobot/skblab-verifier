package com.skblab.leadsapi.models;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Alex Scrobot
 */
@ResponseBody
public class LeadResponse {

    public final long requestId;
    public final String email;

    public LeadResponse(long requestId, String email) {
        this.requestId = requestId;
        this.email = email;
    }
}
