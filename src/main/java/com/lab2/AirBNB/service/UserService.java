package com.lab2.AirBNB.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.lab2.AirBNB.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User findUserByUsername(String username);

    List<User> list();

    void saveUser(User user);

    void updateUser(User user);

    User currentUser();

    User get(Long id);

}
