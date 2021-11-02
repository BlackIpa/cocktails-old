package com.cocktails.service;

import com.cocktails.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RecipeService {

    public List<Recipe> findAll();
//    public Page<Recipe> findAll(int page, int size);

    public List<Recipe> findByNameContaining(String name);
//    public Page<Recipe> findByNameContaining(String name, int page, int size);

    public Recipe findById(Long theId);

//    public List<Recipe> findUserFavourites();
}
