package com.cocktails.dao;

import com.cocktails.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource(collectionResourceRel = "recpieIngredient", path = "recipe-ingredient")
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
}
