package com.cocktails.dao;

import com.cocktails.entity.Ingredient;
import com.cocktails.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("SELECT DISTINCT i.name FROM Ingredient i")
    List<String> getListOfIngredientNames();

    @Query(value = "SELECT i FROM Ingredient i WHERE i.name LIKE %:name%")
    Ingredient findByName(String name);

    List<Ingredient> findAllByOrderByNameAsc();
}
