package com.application.auth.controller;

import com.application.auth.entity.User;
import com.application.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;



    @PreAuthorize("hasRole('CLIENT_ADMIN') or hasRole('ADMIN')")
     public List<User> getUsers() {
        log.info("Getting all users ");
        return userService.getAllUsers();
    }


    @PreAuthorize("hasRole('CLIENT_ADMIN') or hasRole('USER')")
    public User getUser(@PathVariable String id) {
        log.info("Getting user {} ",id);
        return userService.getUserById(id);
    }
}
