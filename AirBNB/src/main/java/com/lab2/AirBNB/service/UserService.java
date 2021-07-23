package com.lab2.AirBNB.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.lab2.AirBNB.model.User;

public interface UserService extends UserDetailsService {

    User findUserByUsername(String username);

    void saveUser(User user);

    User currentUser();
}
