/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.EasyBankProject.controller;

import com.mthree.EasyBankProject.model.User;
import com.mthree.EasyBankProject.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 *
 * @author khali
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User createdUser = userService.registerUser(user);
        if (createdUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/login/email")
    public ResponseEntity<User> loginWithEmail(@RequestParam String email, @RequestParam String password) {
        Optional<User> user = userService.getUserByEmailAndPassword(email, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login/username")
    public ResponseEntity<User> loginWithUsername(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.getUserByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        String currentUsername = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUsername == null || !currentUsername.equals(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);  // As only a logged-in user can update
        }
        
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        String currentUsername = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
        if (currentUsername == null || !currentUsername.equals(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);  // As only a logged-in user can delete
        }

        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}



