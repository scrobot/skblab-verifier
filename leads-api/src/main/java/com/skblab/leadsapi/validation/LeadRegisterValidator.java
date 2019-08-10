package com.skblab.leadsapi.validation;

import com.skblab.leadsapi.models.LeadRequestBody;

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

    public Boolean hasErrors(LeadRequestBody requestBody) {
        return validator.validate(requestBody).size() > 0;
    }

    public List<String> getErrorMessages(LeadRequestBody requestBody) {
        Set<ConstraintViolation<LeadRequestBody>> violations = validator.validate(requestBody);

        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }

}
