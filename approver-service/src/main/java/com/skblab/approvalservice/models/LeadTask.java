package com.skblab.approvalservice.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Alex Scrobot
 */
@Entity
@Data
public class LeadTask {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private final Long requestId;

    @Column
    private boolean isHandled = false;

    @Column
    private boolean isApproved = false;

    @Column
    private final String login;

    public static LeadTask empty() {
        LeadTask leadTask = new LeadTask(-1L, null);
        leadTask.id = -1L;
        return leadTask;
    }

}
