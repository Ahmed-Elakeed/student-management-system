package com.system.studentmanagementsystem.repository;


import com.system.studentmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
