package com.marinerinnovations.users.managers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.marinerinnovations.users.entity.Permission;
import com.marinerinnovations.users.entity.User;
import com.marinerinnovations.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagerImpl implements UserManager {

    private final UserRepository userRepository;
    private final Random rand = SecureRandom.getInstanceStrong();

    @Autowired
    public UserManagerImpl(final UserRepository userRepository) throws NoSuchAlgorithmException {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.getUserMap().values());
    }

    @Override
    public User addUser(final User user) {
        user.setId(rand.nextInt());
        userRepository.getUserMap().putIfAbsent(user.getId(), user);
        return userRepository.getUserMap().get(user.getId());
    }

    @Override
    public boolean deleteUser(final long userId) {
        userRepository.getUserMap().remove(userId);
        return true;
    }

    @Override
    public User getUserById(final long userId) {
        return userRepository.getUserMap().get(userId);
    }

    @Override
    public List<User> getUserByFamilyName(final String familyName) {
        return userRepository.getUserMap().values().stream().filter(user -> user
                        .getFamilyName().equalsIgnoreCase(familyName))
                .collect(Collectors.toList());
    }

    @Override
    public boolean grantAccess(final long userId, final Permission permission) {
        permission.setId(rand.nextInt());
        final var permissions = userRepository.getUserMap().get(userId).getPermissions();
        return permissions.add(permission);
    }

    @Override
    public boolean revokeAccess(final long userId, final long permissionId) {
        final var permissions = new ArrayList<>(userRepository.getUserMap().get(userId).getPermissions());
        var remove = permissions.stream().filter(permission -> permission.getId() == permissionId).collect(Collectors.toList()).get(0);
        permissions.remove(remove);
        userRepository.getUserMap().get(userId).setPermissions(permissions);
        return true;
    }
}
