package com.cocktails.rest;

import com.cocktails.dao.UserRepository;
import com.cocktails.entity.Recipe;
import com.cocktails.entity.User;
import com.cocktails.entity.UserDetailsImpl;
import com.cocktails.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("")
    public RedirectView home() {
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/recipes");
        return redirectView;
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/recipes")
    public String findAll(@RequestParam(required = false) String name,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "6") int size,
                                Model model) {
        try {
            if (name == null) {
                System.out.println("We're in: RecipeRestController findAll method");
//                Page<Recipe> recipes = recipeService.findAll(page, size);
                List<Recipe> recipes = recipeService.findAll();
                // add to the spring model
                model.addAttribute("recipes", recipes);
                System.out.println(recipes);
                return "recipes";
            }
            else {
                System.out.println("We're in: RecipeRestController findByNameContaining method");
//                Page<Recipe> recipes = recipeService.findByNameContaining(name, page, size);
                List<Recipe> recipes = recipeService.findByNameContaining(name);
                model.addAttribute("recipes", recipes);
                return "recipes";
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/recipes/{id}")
    public String getRecipe(@PathVariable Long id, Model model) {
        System.out.println("We're in: RecipeRestController getRecipe method");

        Recipe recipe = recipeService.findById(id);
        System.out.println("Recipe " + recipe);
        model.addAttribute("recipe", recipe);
        return "recipe-detail";
    }

    @GetMapping("/addToFavourites/{recipeId}")
    public String addToFavourites(@PathVariable Long recipeId,
                                  Authentication authentication) {
        System.out.println("We're in: RecipeRestController addToFavourites method");

        Recipe recipe = recipeService.findById(recipeId);
        System.out.println("Recipe: " + recipe.getName());
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("User: " + userDetails.getUsername());
        System.out.println("User: " + userDetails.getId());

        return "recipes";
    }
}






















