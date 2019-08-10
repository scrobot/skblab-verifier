package com.skblab.leadsapi.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Alex Scrobot
 */
public class LeadRequestBody {

    @NotNull(message = "login cannot be null")
    private String login;
    @Size(min = 8, message = "password cannot be shorter then 8 symbols")
    private String password;
    @Email(message = "Email should be valid")
    private String email;
    private String fullname;

    public LeadRequestBody() {}

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFullname() {
        return fullname;
    }
}
