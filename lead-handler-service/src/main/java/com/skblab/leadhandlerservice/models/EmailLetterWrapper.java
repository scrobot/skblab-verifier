package com.skblab.leadhandlerservice.models;

import lombok.Data;

/**
 * @author Alex Scrobot
 */
@Data
public class EmailLetterWrapper {

    private final String topic;
    private final String address;
    private final String text;

}
