package com.cocktails.dao;

import com.cocktails.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByOrderByNameAsc();

    List<Recipe> findByNameContaining(String name);

}