package com.system.studentmanagementsystem.controller;


import com.system.studentmanagementsystem.model.Student;
import com.system.studentmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Secured({"ROLE_USER","ROLE_MANAGER"})
@RequestMapping(path = "/students")
public class StudentController {

    private StudentService studentService;


    @Autowired
    public StudentController(StudentService service) {
        this.studentService = service;
    }


    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER')")
    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "students";
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(path = "/new")
    public String getAddPage(Model model) {
        model.addAttribute("student", new Student());
        return "create_student";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping(path = "/edit/{id}")
    public String getEditPage(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getById(id));
        return "edit_student";
    }
    @GetMapping(path = "/delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteById(id);
        return "redirect:/students";
    }
    @PostMapping
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @PostMapping(path = "/edit/{id}")
    public String editStudent(@ModelAttribute("student") Student student, @PathVariable Long id) {
        studentService.update(id, student);
        return "redirect:/students";
    }
}
