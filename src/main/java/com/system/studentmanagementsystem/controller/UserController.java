package com.system.studentmanagementsystem.controller;

import com.system.studentmanagementsystem.model.User;
import com.system.studentmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(path = "/login")
    public String showLoginPage(){
        return "login";
    }
    @GetMapping(path = "/registration")
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
        return "user_registration";
    }
    @PostMapping(path = "/registration")
    public String registerUserAccount(@ModelAttribute("user")User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:registration?success";
    }
}
