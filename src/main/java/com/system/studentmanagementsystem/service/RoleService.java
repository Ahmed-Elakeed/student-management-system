package com.system.studentmanagementsystem.service;

import com.system.studentmanagementsystem.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAll();
    Role getById(Long id);
    Role save(Role role);
    void deleteById(Long id);
    void deleteAll();
    void update(Long id,Role role);
    Role findByName(String name);
}
