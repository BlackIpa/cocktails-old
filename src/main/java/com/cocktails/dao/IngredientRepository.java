package com.cocktails.dao;

import com.cocktails.entity.Ingredient;
import com.cocktails.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("SELECT DISTINCT i.kind FROM Ingredient i")
    List<String> getListOfIngredientKinds();

    List<Ingredient> findAllByOrderByNameAsc();
}
