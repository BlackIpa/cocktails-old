package com.cocktails.dao;

import com.cocktails.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    // finding recipes by keyword typed by user on frontend
    //Page<Recipe> findByRecipeContaining(@RequestParam("recipe") String recipe, Pageable pageable);

    List<Recipe> findAllByOrderByNameAsc();

    @Query(value = "SELECT r FROM Recipe r")
    List<Recipe> findAllRecipes();
}
