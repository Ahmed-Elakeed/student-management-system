package com.system.studentmanagementsystem.service;

import com.system.studentmanagementsystem.model.Student;

import java.util.List;

public interface StudentService {
     List<Student> getAll();
     Student getById(Long id);
     Student save(Student student);
     void deleteById(Long id);
     void deleteAll();
     void update(Long id,Student student);
}
