package com.cocktails.service;

import com.cocktails.dao.RecipeIngredientRepository;
import com.cocktails.entity.Ingredient;
import com.cocktails.entity.RecipeIngredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private RecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeIngredientServiceImpl (RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Override
    public void save(RecipeIngredient recipeIngredient) {
        System.out.println("We're in: RecipeService save method");
        recipeIngredientRepository.save(recipeIngredient);
    }
}
