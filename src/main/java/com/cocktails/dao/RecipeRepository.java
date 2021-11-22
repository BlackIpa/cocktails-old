package com.cocktails.dao;

import com.cocktails.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByOrderByNameAsc();
//    Page<Recipe> findAllByOrderByNameAsc(Pageable pageable);

    // finding recipes by keyword typed by user on frontend
    @Query(value = "SELECT r FROM Recipe r WHERE r.name LIKE %:name%")
    List<Recipe> findByNameContaining(String name);
//    Page<Recipe> findByNameContaining(String name, Pageable pageable);

}