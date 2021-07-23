package com.lab2.AirBNB.service;

import org.springframework.stereotype.Service;

import com.lab2.AirBNB.model.Role;
import com.lab2.AirBNB.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public Role getRoleName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role getRoleByAuthority(String authority) {
        return null;
    }
}
