/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.EasyBankProject.service.user;

import com.mthree.EasyBankProject.model.User;
import com.mthree.EasyBankProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author khali
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {

        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || !user.getPassword().matches(".+@.+\\..+")) {
            throw new IllegalArgumentException("Please enter a valid email address.");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty nor be whitespace.");
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty nor be whitespace.");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username is already taken.");
        }

        if (user.getPassword() == null || user.getPassword().trim().length() < 6
                || !user.getPassword().matches(".*\\d.*") 
                || !user.getPassword().matches("\\S+.+\\S+") 
                || !user.getPassword().matches(".*[^a-zA-Z0-9\\s].*")) {
            throw new IllegalArgumentException("Password must be at least 6 characters, have at least one number "
                    + "and have at least one special character. It must also not contain any leading or trailing spaces.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserByUsernameAndPassword(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long userId, User user) {

        if (user.getEmail() != null && user.getEmail().trim().isEmpty() || !user.getPassword().matches(".+@.+\\..+") ) {
            throw new IllegalArgumentException("Please enter a valid email.");
        }

        if (user.getName() != null && user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty nor be whitespace.");
        }

        if (user.getPassword() == null || user.getPassword().trim().length() < 6
                || !user.getPassword().matches(".*\\d.*") 
                || !user.getPassword().matches("\\S+.+\\S+") 
                || !user.getPassword().matches(".*[^a-zA-Z0-9\\s].*")) {
            throw new IllegalArgumentException("Password must be at least 6 characters, have at least one number "
                    + "and have at least one special character. It must also not contain any leading or trailing spaces.");
        }

        if (userRepository.existsById(userId)) {
            User existingUser = userRepository.findById(userId).get();

            if (user.getName() != null) {
                existingUser.setName(user.getName());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getBranch() != null && !user.getBranch().trim().isEmpty()) {
                existingUser.setBranch(user.getBranch());
            }
            if (user.getAddress() != null && !user.getAddress().trim().isEmpty()) {
                existingUser.setAddress(user.getAddress());
            }
            
            if (user.getPassword() != null) {
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                existingUser.setPassword(encodedPassword);
            }

            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    @Override
    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        }
    }
}