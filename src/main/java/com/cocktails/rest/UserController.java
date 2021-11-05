package com.cocktails.rest;

import com.cocktails.entity.Recipe;
import com.cocktails.entity.User;
import com.cocktails.entity.UserDetailsImpl;
import com.cocktails.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    	System.out.println("We're in UserController viewLoginPage method");
    	return "login";
    }

    @GetMapping("/account")
    public String viewUserDetails(Model model) {
        System.out.println("We're in UserController viewUserDetails method");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findById(userDetails.getId());

        model.addAttribute("user", user);

        return "account";
    }

    @GetMapping("/favourites")
    public String getFavourites(Model model) {
        System.out.println("We're in UserController getFavourites method");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.findById(userDetails.getId());
        Set<Recipe> recipes = user.getFavouriteRecipes();

        model.addAttribute("recipes", recipes);

        return "favourites";
    }

    @PostMapping("/addToFavourites")
    public String addToFavourites(@ModelAttribute("recipe") Recipe recipe) {

        System.out.println("We're in UserController addToFavourites method");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("MODEL ATTRIBUTE RECIPE is " + recipe);
        System.out.println("User id is: " + userDetails.getId());
        userService.addToFavourites(recipe, userDetails.getId());

        return "recipes";
    }
}
