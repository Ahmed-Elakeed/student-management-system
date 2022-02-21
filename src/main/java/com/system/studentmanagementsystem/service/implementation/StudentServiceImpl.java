package com.system.studentmanagementsystem.service.implementation;


import com.system.studentmanagementsystem.model.Student;
import com.system.studentmanagementsystem.repository.StudentDAO;
import com.system.studentmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> getAll() {
        return studentDAO.findAll();
    }

    @Override
    public Student getById(Long id) {
        return studentDAO.getById(id);
    }

    @Override
    public Student save(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public void deleteById(Long id) {
        studentDAO.deleteById(id);
    }

    @Override
    public void deleteAll() {
        studentDAO.deleteAll();
    }

    @Override
    @Transactional
    public synchronized void update(Long id,Student student) {
        Student updatedStudent=studentDAO.getById(id);
        updatedStudent.setFirstName(student.getFirstName());
        updatedStudent.setLastName(student.getLastName());
        updatedStudent.setEmail(student.getEmail());
    }
}
