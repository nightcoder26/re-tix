package com.example.retix.service;

import com.example.retix.model.User;
import com.example.retix.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to create a user
    public User createUser(User user) {
        return userRepository.save(user); // Saves the new user into the database
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public User updateUser(User user) {
        // Check if the user with the given id exists
        Optional<User> existingUserOptional = userRepository.findById(user.getId());
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Update the necessary fields
            existingUser.setEmail(user.getEmail());
            existingUser.setFullName(user.getFullName());
            existingUser.setActive(user.isActive());
            existingUser.setPassword(user.getPassword());
            existingUser.setUsername(user.getUsername());

            return userRepository.save(existingUser);
        } else {
            throw new EntityNotFoundException("User not found with id: " + user.getId());
        }
    }

    public User deleteUserById(Long id) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            userRepository.delete(existingUser);
            return existingUser;

        }
        throw new EntityNotFoundException("User not found with id: " + id);
    }


}
