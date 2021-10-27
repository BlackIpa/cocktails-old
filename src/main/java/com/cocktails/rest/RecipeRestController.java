package com.cocktails.rest;

import com.cocktails.entity.Recipe;
import com.cocktails.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RecipeRestController {

    private RecipeService recipeService;

    @Autowired
    public RecipeRestController(RecipeService theRecipeService) {
        recipeService = theRecipeService;
    }

    @GetMapping("/recipes")
    public Page<Recipe> findAll(@RequestParam(required = false) String name,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "4") int size) {
        System.out.println("We're in: RecipeRestController findAll method");

//        try {
            if (name == null) {
                return recipeService.findAll(page, size);
            }
            else {
                return recipeService.findByNameContaining(name, page, size);
            }
//        }
//        catch (Exception e) {
//            return new
//        }
    }

    @GetMapping("/recipes/{recipeId}")
    public Recipe getRecipe(@PathVariable Long recipeId) {
        System.out.println("We're in: RecipeRestController findAll method");

        Recipe theRecipe = recipeService.findById(recipeId);
        return theRecipe;
    }
}






















