package com.cocktails.service;

import com.cocktails.dao.RecipeRepository;
import com.cocktails.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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


    @Override
    public List<Recipe> findByNameContaining(String name) {
        return recipeRepository.findByNameContaining(name);
    }

    @Override
    // recipeDTO zamiast Recipe w parametrze
    public void save(Recipe recipe) {
        recipe.getRecipeIngredients().forEach(recipeIngredient -> recipeIngredient.setRecipes(recipe));
        recipeRepository.save(recipe);
    }
}
