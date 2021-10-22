package com.cocktails.service;

import com.cocktails.dao.RecipeRepository;
import com.cocktails.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository theRecipeRepository) {
        recipeRepository = theRecipeRepository;
    }

    @Override
    public List<Recipe> findAll() {
        System.out.println("We're in: RecipeService");
        return recipeRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Recipe findById(Long theId) {
        Optional<Recipe> result = recipeRepository.findById(theId);

        Recipe theRecipe = null;
        if (result.isPresent()) {
            theRecipe = result.get();
        }
        else {
            // Didn't find the employee
            throw new RuntimeException("Didn't find the recipe id - " + theId);
        }
        return theRecipe;
    }
}
