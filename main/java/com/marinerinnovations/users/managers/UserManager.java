package com.marinerinnovations.users.managers;

import java.util.List;

import com.marinerinnovations.users.entity.Permission;
import com.marinerinnovations.users.entity.User;

/**
 * UserManager, responsible for all the user management services
 */
public interface UserManager {
    /**
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * @param user user
     * @return user with its id
     */
    User addUser(final User user);

    /**
     * @param userId user id
     * @return true if deleted successfully, otherwise false
     */
    boolean deleteUser(final long userId);

    /**
     * @param userId user id
     * @return user object
     */
    User getUserById(final long userId);

    /**
     * @param familyName user family name
     * @return list of all the users whose family name is same
     */
    List<User> getUserByFamilyName(final String familyName);

    /**
     * @param userId user id
     * @param permission permission to access user details
     * @return true if permission added successfully
     */
    boolean grantAccess(final long userId, final Permission permission);

    /**
     * @param userId user id
     * @param permissionId permission id
     * @return true if permission deleted successfully
     */
    boolean revokeAccess(final long userId,final long permissionId);
}
