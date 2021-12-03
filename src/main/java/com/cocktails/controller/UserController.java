package com.cocktails.controller;

import com.cocktails.entity.User;
import com.cocktails.entity.UserDetailsImpl;
import com.cocktails.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    private UserDetailsService userDetailsService;
    private UserService userService;

    @Autowired
    public UserController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/signup")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/processSignup")
    public String processSignup(User user) {
        userService.saveUser(user);
        return "signup-success";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    
    @GetMapping("/login")
    public String viewLoginPage() {
    	return "login";
    }

    @GetMapping("/account")
    public String viewUserDetails(Model model) {
        User user = getAuthenticatedUser();
        model.addAttribute("user", user);
        return "account";
    }

    private User getAuthenticatedUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userService.findById(userDetails.getId());
    }
}
