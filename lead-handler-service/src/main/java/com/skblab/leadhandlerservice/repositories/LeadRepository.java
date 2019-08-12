package com.skblab.leadhandlerservice.repositories;

import com.skblab.leadhandlerservice.models.Lead;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Scrobot
 */
public interface LeadRepository extends CrudRepository<Lead, Long> {
}
