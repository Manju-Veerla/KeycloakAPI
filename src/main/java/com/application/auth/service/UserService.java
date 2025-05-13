package com.application.auth.service;


import com.application.auth.entity.User;
import com.application.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;


    public User getUser(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("User not found with given username: " + userName));
       }

    public User getUserById(String id) {
        User user = userRepository.findById(Integer.parseInt(id))
                .orElseThrow(() -> new IllegalArgumentException("User not found with given id: " + id));
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .toList();
    }
}
