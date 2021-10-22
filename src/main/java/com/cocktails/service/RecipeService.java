package com.cocktails.service;

import com.cocktails.entity.Recipe;

import java.util.List;

public interface RecipeService {

    public List<Recipe> findAll();

    public Recipe findById(Long theId);
}
