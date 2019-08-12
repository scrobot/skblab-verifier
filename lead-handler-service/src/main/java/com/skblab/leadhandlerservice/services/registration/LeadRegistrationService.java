package com.skblab.leadhandlerservice.services.registration;

import com.skblab.leadhandlerservice.models.Lead;
import com.skblab.protoapi.LeadHandleRequest;

/**
 * @author Alex Scrobot
 */
public interface LeadRegistrationService {

    Lead registerLead(LeadHandleRequest request);

}
