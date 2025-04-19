package com.example.retix.service;

import com.example.retix.model.User;
import com.example.retix.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        User saved = userRepository.save(user);
        System.out.println("✅ Saved user in DB with ID: " + saved.getId());
        return saved;
    }
    

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update user by ID
    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setRole(user.getRole());
            return userRepository.save(existingUser);
        });
    }
    

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }  

    public List<User> getAllUsers() {
        System.out.println("Fetching all users");
    try {
        return userRepository.findAll();
    } catch (Exception e) {
        System.err.println("Error fetching all users: " + e.getMessage());
        return List.of();
    }
    }
}
