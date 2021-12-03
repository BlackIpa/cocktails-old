package com.cocktails.service;

import com.cocktails.dao.RecipeRepository;
import com.cocktails.dao.UserRepository;
import com.cocktails.entity.Recipe;
import com.cocktails.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findById(Long theId) {
        User user = userRepository.getById(theId);
        return user;
    }

    @Override
    public void saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public void toggleToFavourites(Recipe recipe, User user) {
        user.toggleRecipeToFavourites(recipe);
        userRepository.save(user);
    }

    @Override
    public void addToCustom(Recipe recipe, User user) {
        user.addToCustom(recipe);
        userRepository.save(user);
    }
}
