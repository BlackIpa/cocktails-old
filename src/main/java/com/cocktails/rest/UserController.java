package com.cocktails.rest;

import com.cocktails.dao.UserRepository;
import com.cocktails.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/process-signup")
    public String processSignup(User user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return "signup-success";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {

        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        System.out.println(listUsers);
        return "users";
    }
}
