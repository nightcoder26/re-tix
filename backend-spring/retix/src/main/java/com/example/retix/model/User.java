package com.example.retix.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates ID
    private Long id;

    @Column(nullable = false, unique = true) // Ensures username is unique
    private String username;

    @Column(nullable = false, unique = true) // Ensures email is unique
    private String email;

    @Column(nullable = false) // Store encrypted passwords
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // Allows roles to be fetched eagerly
    private Set<String> roles;

    @Column(nullable = false) // Ensures the field cannot be null
    private boolean isActive = true; // Default value

    private String fullName; // Optional field for full name

    // Constructors
    public User() {}

    public User(String username, String email, String password, Set<String> roles, boolean isActive, String fullName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.isActive = isActive;
        this.fullName = fullName;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
