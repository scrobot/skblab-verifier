package com.skblab.leadsapi.models;

import lombok.Data;
import lombok.val;

/**
 * @author Alex Scrobot
 */
@Data
public class LeadRequestBody {

    private val login;
    private val password;
    private val email;
    private val fullname;

}
