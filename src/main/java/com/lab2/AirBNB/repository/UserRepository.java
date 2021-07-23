package com.lab2.AirBNB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab2.AirBNB.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}

