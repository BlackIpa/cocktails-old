package com.cocktails.rest;

import com.cocktails.dao.UserRepository;
import com.cocktails.entity.Recipe;
import com.cocktails.entity.User;
import com.cocktails.entity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private UserDetailsService userDetailsService;

    @GetMapping("/signup")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/processSignup")
    public String processSignup(User user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return "signup-success";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
    	
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }
    
    @GetMapping("/login")
    public String viewLoginPage() {
    	System.out.println("We're in UserController viewLoginPage method");
    	return "login";
    }

    @GetMapping("/account")
    public String viewUserDetails(Model model) {
        System.out.println("We're in UserController viewUserDetails method");
        UserDetailsImpl user =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", user);

        return "account";
    }

    @GetMapping("/favourites")
    public String getFavourites(Model model) {
        System.out.println("We're in UserController getFavourites method");
        UserDetailsImpl user =
                (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Set<Recipe> recipes = user.getFavourites();

//        model.addAttribute("recipes", recipes);

        return "favourites";
    }
}
