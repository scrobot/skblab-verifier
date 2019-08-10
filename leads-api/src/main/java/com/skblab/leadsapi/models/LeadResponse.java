package com.skblab.leadsapi.models;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Alex Scrobot
 */
@ResponseBody
public class LeadResponse {

    public long requestId = 0;
    public String email;

}
