package com.system.studentmanagementsystem.service.implementation;


import com.system.studentmanagementsystem.model.Role;
import com.system.studentmanagementsystem.model.User;
import com.system.studentmanagementsystem.repository.UserDAO;
import com.system.studentmanagementsystem.service.RoleService;
import com.system.studentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleService roleService) {
        this.userDAO = userDAO;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @Override
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    @Override
    public User save(User user) {
        user.setRoles(new HashSet<>(Arrays.asList(roleService.findByName("ROLE_MANAGER"))));
        return userDAO.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userDAO.deleteAll();
    }

    @Override
    @Transactional
    public void update(Long id, User user) {
        User updatedUser = userDAO.getById(id);
        updatedUser.setEmail(user.getEmail());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPassword(user.getPassword());
    }

    @Override
    @Transactional
    public void addRole(User user, Role role) {
        User updateUser = userDAO.getById(user.getId());
        Role updatedRole = roleService.getById(role.getId());
        updateUser.getRoles().add(updatedRole);
        updatedRole.getUsers().add(updateUser);
    }

    @Override
    @Transactional
    public void removeRole(User user, Role role) {
        User updateUser = userDAO.getById(user.getId());
        Role updatedRole = roleService.getById(role.getId());
        updateUser.getRoles().remove(updatedRole);
        updatedRole.getUsers().remove(updateUser);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
