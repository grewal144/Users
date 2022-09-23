package com.marinerinnovations.users.entity;

import java.util.Date;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/**
 * user permission
 */
@Data
@Jacksonized
public class Permission {

    private long id;

    private String type;

    private Date grantedOn;
}
