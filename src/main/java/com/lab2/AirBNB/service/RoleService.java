package com.lab2.AirBNB.service;

import com.lab2.AirBNB.model.Role;

public interface RoleService {
    Role getRoleName(String name);

    Role getRoleByAuthority(String authority);
}
