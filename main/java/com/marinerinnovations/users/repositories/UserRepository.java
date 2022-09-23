package com.marinerinnovations.users.repositories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.marinerinnovations.users.entity.User;
import org.springframework.stereotype.Component;

/**
 * Storing all the users in in-memory
 */
@Component
public class UserRepository {
    /**
     * userMap contains all the user with its permissions
     */
    private final Map<Long, User> userMap = new ConcurrentHashMap<>();

    public Map<Long, User> getUserMap() {
        return userMap;
    }

}
