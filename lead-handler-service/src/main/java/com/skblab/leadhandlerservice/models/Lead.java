package com.skblab.leadhandlerservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Alex Scrobot
 */
@Entity
public class Lead {

    @Id
    @GeneratedValue
    private long requestId;
    @Column
    public final String email;
    @Column(nullable = true)
    public final String fullname;
    @Column
    public final String login;
    @Column
    public final String password;

    public Lead(String email, String fullname, String login, String password) {
        this.email = email;
        this.fullname = fullname;
        this.login = login;
        this.password = password;
    }

    public long getRequestId() {
        return requestId;
    }

}
