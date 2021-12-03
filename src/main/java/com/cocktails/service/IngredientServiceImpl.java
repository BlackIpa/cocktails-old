package com.cocktails.service;

import com.cocktails.dao.IngredientRepository;
import com.cocktails.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> getListOfIngredientNames() {
        return ingredientRepository.getListOfIngredientNames();
    }

    @Override
    public Ingredient findByName(String name) {
        return ingredientRepository.findByNameContaining(name);
    }
}
