package com.reljicd.service;

import com.reljicd.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User saveUser(User user);

    User saveUserWithRole(User user, String roleName);

    List<User> findAllUsers();

}
