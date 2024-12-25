package com.example.retix.service;

import com.example.retix.model.User;
import com.example.retix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Register a new user with encrypted password
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by their ID
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Find a user by their username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Save or update a user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Delete a user by their ID
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}

