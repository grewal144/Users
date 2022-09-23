package com.marinerinnovations.users.controllers;

import java.util.List;

import com.marinerinnovations.users.entity.Permission;
import com.marinerinnovations.users.entity.User;
import com.marinerinnovations.users.managers.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for providing all the resources for user services
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserManager userManager;

    @Autowired
    public UserController(final UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return userManager.getAllUsers();
    }

    @PostMapping(value = "/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody final User user) {
        return userManager.addUser(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") final long id) {
        userManager.deleteUser(id);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable("id") final long id) {
        return userManager.getUserById(id);
    }

    @PostMapping(value = "/user/{id}/permission",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void grantPermission(@PathVariable("id") final long id, @RequestBody final Permission permission) {
        userManager.grantAccess(id, permission);
    }

    @DeleteMapping("/user/{id}/permission/{permissionId}")
    public void revokePermission(@PathVariable("id") final long id, @PathVariable("permissionId") final long permissionId) {
        userManager.revokeAccess(id, permissionId);
    }

    @GetMapping(value = "/users/{familyName}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUserByFamilyName(@PathVariable("familyName") String familyName) {
        return userManager.getUserByFamilyName(familyName);
    }

}
