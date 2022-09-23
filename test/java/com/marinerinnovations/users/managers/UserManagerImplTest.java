package com.marinerinnovations.users.managers;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.marinerinnovations.users.controllers.UserController;
import com.marinerinnovations.users.entity.Permission;
import com.marinerinnovations.users.entity.User;
import com.marinerinnovations.users.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserManagerImplTest {

    private UserManager userManager;
    private User user;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        userManager = new UserManagerImpl(new UserRepository());
        user = new User();
        user.setBirthdate(new Date());
        user.setEmail("abc@g.com");
        user.setFamilyName("Roy");
        user.setGivenName("Jony");
        final var permission = new Permission();
        permission.setType("admin");
        permission.setGrantedOn(new Date());
        final var permissions = new ArrayList<Permission>();
        permissions.add(permission);
        user.setPermissions(permissions);
    }

    @Test
    void getAllUsers() {
        // add user first before accessing list of users
        var id  = userManager.addUser(user).getId();

        var result = userManager.getAllUsers();
        Assertions.assertEquals(Collections.singletonList(user),
                result.stream().filter(tempUser -> tempUser.getId() == id).collect(Collectors.toList()));
    }

    @Test
    void addUser() {
        // add user first before accessing list of users
        var result  = userManager.addUser(user);
        Assertions.assertEquals(user, result);
    }

    @Test
    void deleteUser() {
        // add user first before accessing list of users
        var addUser  = userManager.addUser(user);
        Assertions.assertEquals(user, addUser);

        var result  = userManager.deleteUser(addUser.getId());
        assertTrue(result);
    }

    @Test
    void getUserById() {
        // add user first before accessing list of users
        var addUser  = userManager.addUser(user);
        Assertions.assertEquals(user, addUser);

        var result  = userManager.getUserById(addUser.getId());
        Assertions.assertEquals(user, result);
    }

    @Test
    void getUserByFamilyName() {
        // add two user first before accessing list of users
        List<User> compareList = new ArrayList<>();
        compareList.add(userManager.addUser(user));
        compareList.add(userManager.addUser(user));

        var result  = userManager.getUserByFamilyName(user.getFamilyName());
        Assertions.assertEquals(compareList, result);
    }

    @Test
    void grantAccess() {
        // add two user first before accessing list of users
        userManager.addUser(user);

        final var permission = new Permission();
        permission.setType("non_admin");
        permission.setGrantedOn(new Date());
        var result  = userManager.grantAccess(user.getId(),permission);
        Assertions.assertTrue(result);
    }

    @Test
    void revokeAccess() {
        // add two user first before accessing list of users
        userManager.addUser(user);

        final var permission = new Permission();
        permission.setId(2);
        permission.setType("non_admin");
        permission.setGrantedOn(new Date());
        var result  = userManager.grantAccess(user.getId(),permission);
        Assertions.assertTrue(result);

        //delete user
        var finalResult  = userManager.revokeAccess(user.getId(),permission.getId());
        Assertions.assertTrue(finalResult);
    }
}
