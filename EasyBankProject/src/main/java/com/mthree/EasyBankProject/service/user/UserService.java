/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.EasyBankProject.service.user;

import com.mthree.EasyBankProject.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author khali
 */

public interface UserService {

    User registerUser(User user);
    Optional<User> getUserById(Long userId);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByEmailAndPassword(String email, String password);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByUsernameAndPassword(String username, String password);
    List<User> getAllUsers();
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);

}
