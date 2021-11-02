package com.cocktails.service;

import com.cocktails.dao.RecipeRepository;
import com.cocktails.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
//    public List<Recipe> findAll(int page, int size) {
        System.out.println("We're in: RecipeService findAll method");
//        Pageable paging = PageRequest.of(page, size);

        return recipeRepository.findAllByOrderByNameAsc();
//        return recipeRepository.findAllByOrderByNameAsc(paging);
    }

    @Override
    public Recipe findById(Long theId) {
        System.out.println("We're in: RecipeService findById method");
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

//    @Override
//    public List<Recipe> findUserFavourites() {
//        return recipeRepository.findUserFavourites();
//    }

    @Override
//    public Page<Recipe> findByNameContaining(String name, int page, int size) {
    public List<Recipe> findByNameContaining(String name) {
        System.out.println("We're in: RecipeService findByNameLike method");
//        Pageable paging = PageRequest.of(page, size);
        return recipeRepository.findByNameContaining(name);
//        return recipeRepository.findByNameContaining(name, paging);
    }
}
