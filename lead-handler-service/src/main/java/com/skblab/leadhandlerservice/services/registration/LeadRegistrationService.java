package com.skblab.leadhandlerservice.services.registration;

import com.skblab.leadhandlerservice.models.Lead;
import com.skblab.protoapi.LeadHandleRequest;

/**
 * @author Alex Scrobot
 */
public interface LeadRegistrationService {

    /**
     * Регистрирует заявку на последующую обработку
     *
     * @param request protobuff-модель с телом запроса
     *
     * @return mapped-модель созданную из параметра
     * */
    Lead registerLead(LeadHandleRequest request);

}
