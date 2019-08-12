package com.skblab.leadhandlerservice.services.registration;

import com.skblab.leadhandlerservice.models.Lead;
import com.skblab.leadhandlerservice.repositories.LeadRepository;
import com.skblab.protoapi.LeadHandleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Alex Scrobot
 */
@Service
public class LeadRegistrationServiceImpl implements LeadRegistrationService {

    @Autowired
    private LeadRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Lead registerLead(LeadHandleRequest request) {
        return repository.save(
                new Lead(
                        request.getEmail(),
                        request.getFullname().getValue(),
                        request.getLogin(),
                        passwordEncoder.encode(request.getPassword())
                )
        );
    }

}
