package com.system.studentmanagementsystem;

import com.system.studentmanagementsystem.model.Student;
import com.system.studentmanagementsystem.service.RoleService;
import com.system.studentmanagementsystem.service.StudentService;
import com.system.studentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner {

    private UserService userService;

    private RoleService roleService;

    private StudentService studentService;


    @Autowired
    public StudentManagementSystemApplication(UserService userService, RoleService roleService, StudentService studentService) {
        this.userService = userService;
        this.roleService = roleService;
        this.studentService = studentService;
    }


    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
