package com.cocktails.service;

import com.cocktails.dao.RecipeRepository;
import com.cocktails.dao.UserRepository;
import com.cocktails.entity.Recipe;
import com.cocktails.entity.User;
import com.cocktails.entity.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<User> findAll() {
        System.out.println("We're in UserService findAll method");
        List<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public User findById(Long theId) {
        System.out.println("We're in UserService findById method");

        User user = userRepository.getById(theId);

        return user;
    }

    @Override
    public void saveUser(User user) {
        System.out.println("We're in UserService saveUser method");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    @Override
    public void toggleToFavourites(Long recipeId, Long userId) {
        System.out.println("We're in UserService addToFavourites method");
        Recipe recipe = recipeRepository.getById(recipeId);
        User user = userRepository.getById(userId);
        System.out.println("Fetched recipe: " + recipe);
        System.out.println("Fetched user: " + user);

        // add saving recipe to user favourites
        user.toggleRecipeToFavourites(recipe);
        userRepository.save(user);
    }
}
