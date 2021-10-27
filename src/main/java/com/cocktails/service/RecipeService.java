package com.cocktails.service;

import com.cocktails.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RecipeService {

    public Page<Recipe> findAll(int page, int size);
//    public List<Recipe> findAll(String recipe, int page, int size);

    public Page<Recipe> findByNameContaining(String name, int page, int size);

    public Recipe findById(Long theId);


}
