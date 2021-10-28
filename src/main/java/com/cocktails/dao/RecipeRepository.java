package com.cocktails.dao;

import com.cocktails.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Page<Recipe> findAllByOrderByNameAsc(Pageable pageable);

    // finding recipes by keyword typed by user on frontend
    @Query(value = "SELECT r FROM Recipe r WHERE r.name LIKE %:name%")
    Page<Recipe> findByNameContaining(String name, Pageable pageable);
}
