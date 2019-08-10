package com.skblab.leadsapi.validation;

import com.skblab.leadsapi.models.LeadRequest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alex Scrobot
 */
public class LeadRegisterValidator {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public Boolean hasErrors(LeadRequest requestBody) {
        return validator.validate(requestBody).size() > 0;
    }

    public List<String> getErrorMessages(LeadRequest requestBody) {
        Set<ConstraintViolation<LeadRequest>> violations = validator.validate(requestBody);

        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

}
