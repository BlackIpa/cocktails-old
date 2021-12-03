package com.cocktails.service.mapper;

import com.cocktails.controller.model.RecipeDTO;
import com.cocktails.entity.Recipe;
import com.cocktails.entity.RecipeIngredient;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeMapper {

    public Recipe toRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        Set<RecipeIngredient> recipeIngredients = new HashSet<>();

        for (int i = 0; i < recipeDTO.getIngredients().size(); i++) {
            recipeIngredients.add(new RecipeIngredient(recipeDTO.getIngredients().get(i),
                                                    recipeDTO.getAmounts().get(i),
                                                    recipeDTO.getUnits().get(i)));
        }

        recipe.setName(recipeDTO.getName());
        recipe.setInstruction(recipeDTO.getInstruction());
        recipe.setPhoto("/images/old-fashioned.png");
        recipe.setGlassId(recipeDTO.getGlassId());
        recipe.setUserCreated(true);
        recipe.setGlassId(1L);
        recipe.setIceMixing(recipeDTO.isIceMixing());
        recipe.setIceGlass(recipeDTO.isIceGlass());
        recipe.setRecipeIngredients(recipeIngredients);

        return recipe;
    }
}
