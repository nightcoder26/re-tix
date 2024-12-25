package com.example.retix.service;

import com.example.retix.model.User;
import com.example.retix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to create a user
    public User createUser(User user) {
        return userRepository.save(user); // Saves the new user into the database
    }
}
