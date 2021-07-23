package com.lab2.AirBNB.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab2.AirBNB.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
