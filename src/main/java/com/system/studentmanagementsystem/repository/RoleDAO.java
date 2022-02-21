package com.system.studentmanagementsystem.repository;

import com.system.studentmanagementsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping
public interface RoleDAO extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
