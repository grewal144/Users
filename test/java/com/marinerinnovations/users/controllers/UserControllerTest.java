package com.marinerinnovations.users.controllers;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

import com.marinerinnovations.users.entity.Permission;
import com.marinerinnovations.users.entity.User;
import com.marinerinnovations.users.managers.UserManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private UserController userController;
    @Mock
    private UserManager mockUserManager;
    private User user;

    @BeforeEach
    void setUp() {
        userController = new UserController(mockUserManager);
        user = new User();
        user.setBirthdate(new Date());
        user.setEmail("abc@g.com");
        user.setFamilyName("Roy");
        user.setGivenName("Jony");
        user.setId(1);
        final var permission = new Permission();
        permission.setId(1);
        permission.setType("admin");
        permission.setGrantedOn(new Date());
        user.setPermissions(Collections.singletonList(permission));
    }

    @Test
    @DisplayName("getUsers() - get all the users")
    void getUsers() {
        Mockito.when(mockUserManager.getAllUsers()).thenReturn(Collections.singletonList(user));
        var result = userController.getUsers();
        Assertions.assertEquals(Collections.singletonList(user),
                result.stream().filter(tempUser -> tempUser.getId() == 1).collect(Collectors.toList()));
    }

    @Test
    void addUser() {
        Mockito.when(mockUserManager.addUser(Mockito.any())).thenReturn(user);
        var result = userController.addUser(user);
        Assertions.assertEquals(user,result);
    }

    @Test
    void deleteUser() {
        Mockito.when(mockUserManager.deleteUser(1)).thenReturn(true);
        userController.deleteUser(1);
        Mockito.verify(mockUserManager, Mockito.times(1)).deleteUser(1);
    }

    @Test
    void getUserById() {
        Mockito.when(mockUserManager.getUserById(1)).thenReturn(user);
        var result = userController.getUserById(1);
        Assertions.assertEquals(user,result);
    }

    @Test
    void grantPermission() {
        final var permission = new Permission();
        Mockito.when(mockUserManager.grantAccess(1,permission)).thenReturn(true);
        userController.grantPermission(1,permission);
        Mockito.verify(mockUserManager, Mockito.times(1)).grantAccess(1,permission);
    }

    @Test
    void revokePermission() {
        final var permission = new Permission();
        Mockito.when(mockUserManager.revokeAccess(1,1)).thenReturn(true);
        userController.revokePermission(1,1);
        Mockito.verify(mockUserManager, Mockito.times(1)).revokeAccess(1,1);
    }

    @Test
    void getUserByFamilyName() {
        Mockito.when(mockUserManager.getUserByFamilyName(user.getFamilyName())).thenReturn(Collections.singletonList(user));
        var result = userController.getUserByFamilyName(user.getFamilyName());
        Assertions.assertEquals(Collections.singletonList(user),
                result.stream().filter(tempUser -> tempUser.getId() == 1).collect(Collectors.toList()));
    }
}
