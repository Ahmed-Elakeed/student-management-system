package com.system.studentmanagementsystem.service.implementation;

import com.system.studentmanagementsystem.model.Role;
import com.system.studentmanagementsystem.repository.RoleDAO;
import com.system.studentmanagementsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }


    @Override
    public List<Role> getAll() {
        return roleDAO.findAll();
    }

    @Override
    public Role getById(Long id) {
        return roleDAO.getById(id);
    }

    @Override
    public Role save(Role role) {
        return roleDAO.save(role);
    }

    @Override
    public void deleteById(Long id) {
        roleDAO.deleteById(id);
    }

    @Override
    public void deleteAll() {
        roleDAO.deleteAll();
    }

    @Override
    public void update(Long id, Role role) {
        Role updateRole=roleDAO.getById(id);
        updateRole.setName(role.getName());
        roleDAO.save(updateRole);
    }

    @Override
    public Role findByName(String name) {
        return roleDAO.findByName(name);
    }
}
