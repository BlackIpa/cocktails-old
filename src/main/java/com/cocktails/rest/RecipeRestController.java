package com.cocktails.rest;

import com.cocktails.entity.Recipe;
import com.cocktails.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RecipeRestController {

    private RecipeService recipeService;

    @Autowired
    public RecipeRestController(RecipeService theRecipeService) {
        recipeService = theRecipeService;
    }

    @GetMapping("/recipes")
    public List<Recipe> findAll() {
        System.out.println("We're in: RecieRestController");
        return recipeService.findAll();
    }

    @GetMapping("/recipes/{recipeId}")
    public Recipe getRecipe(@PathVariable Long recipeId) {

        Recipe theRecipe = recipeService.findById(recipeId);

        if (theRecipe == null) {
            throw new RuntimeException("Employee id not found - " + recipeId);
        }

        return theRecipe;
    }

}
