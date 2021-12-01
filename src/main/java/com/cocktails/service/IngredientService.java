package com.cocktails.service;

import com.cocktails.entity.Ingredient;

import java.util.List;

public interface IngredientService {

    public List<Ingredient> findByNameContaining(String name);

    public List<String> getListOfIngredientNames();

    public Ingredient findByName(String name);
}
