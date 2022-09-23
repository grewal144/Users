package com.marinerinnovations.users.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * user entity
 */
@Data
@Jacksonized
public class User {
    private Date birthdate;
    private long id;
    private String familyName;
    private String givenName;
    private String email;
    private Collection<Permission> permissions = new ArrayList<>();

}
