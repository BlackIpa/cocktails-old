package com.cocktails.dao;

import com.cocktails.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://localhost:4200")
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Page<Recipe> findByBaseId(@RequestParam("id") Long id, Pageable pageable);
}
