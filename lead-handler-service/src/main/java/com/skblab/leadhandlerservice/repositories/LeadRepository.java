package com.skblab.leadhandlerservice.repositories;

import com.skblab.leadhandlerservice.models.Lead;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author Alex Scrobot
 */
public interface LeadRepository extends CrudRepository<Lead, Long> {
}
