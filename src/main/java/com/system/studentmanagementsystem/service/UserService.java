package com.system.studentmanagementsystem.service;

import com.system.studentmanagementsystem.model.Role;
import com.system.studentmanagementsystem.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAll();
    User getById(Long id);
    User save(User user);
    void deleteById(Long id);
    void deleteAll();
    void update(Long id,User user);
    void addRole(User user, Role role);
    void removeRole(User user, Role role);
    User findByEmail(String email);
}
