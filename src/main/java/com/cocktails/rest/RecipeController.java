package com.cocktails.rest;

import com.cocktails.dao.IngredientRepository;
import com.cocktails.entity.Ingredient;
import com.cocktails.entity.Recipe;
import com.cocktails.entity.User;
import com.cocktails.entity.UserDetailsImpl;
import com.cocktails.service.RecipeService;
import com.cocktails.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class RecipeController {

    private RecipeService recipeService;
    private UserService userService;
    private IngredientRepository ingredientRepository;
    private List<Long> userFavouriteRecipes = new ArrayList<Long>();

    @Autowired
    public RecipeController(RecipeService recipeService,
                            UserService userService,
                            IngredientRepository ingredientRepository) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.ingredientRepository = ingredientRepository;
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
    public String findRecipes(@RequestParam(required = false) String name,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "6") int size,
                                Principal currentUser,
                                Model model) {

        if (currentUser != null) {
            userFavouriteRecipes = getFavouriteRecipeIds();
        }
        System.out.println(userFavouriteRecipes);
        model.addAttribute("favouriteIds", userFavouriteRecipes);
        try {
            if (name == null) {
                System.out.println("We're in: RecipeRestController findAll method");
//                Page<Recipe> recipes = recipeService.findAll(page, size);
                List<Recipe> recipes = recipeService.findAll();
                // add to the spring model
                model.addAttribute("recipes", recipes);
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

    public List<Long> getFavouriteRecipeIds() {
        List<Long> list = new ArrayList<>();

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userService.findById(userDetails.getId());

        for (Recipe rec : user.getFavouriteRecipes()) {
            System.out.println(rec.getName());
            list.add(rec.getRecipeId());
        }
        return list;
    }

    @GetMapping("/favourites")
    public String getFavourites(Model model) {
        System.out.println("We're in RecipeController getFavourites method");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User user = userService.findById(userDetails.getId());
        Set<Recipe> recipes = user.getFavouriteRecipes();

        model.addAttribute("recipes", recipes);

        return "favourites";
    }

    @GetMapping("/custom")
    public String getCustomRecipes (Model model) {
        System.out.println("We're in RecipeController getCustomRecipes method");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User user = userService.findById(userDetails.getId());

        Set<Recipe> recipes = user.getCustomRecipes();
        model.addAttribute("recipes", recipes);

        System.out.println("We're in: RecipeRestController getCustomRecipes method");
        return "custom";
    }

    @GetMapping("/create-cocktail")
    public String showFormForCocktailCreation(Model model) {
        System.out.println("We're in RecipeController showFormForCocktailCreation method");
        Recipe recipe = new Recipe();
        List<Ingredient> ingredients = ingredientRepository.findAllByOrderByNameAsc();

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);

        return "cocktail-form";
    }

    @PostMapping("/save-cocktail")
    public String saveCocktail(@ModelAttribute("recipe") Recipe recipe,
                               @ModelAttribute("ingredients") Ingredient ingredients) {
        System.out.println("We're in RecipeController saveCocktail method");

        recipe.setUserCreated(true);
        recipe.setPhoto("/images/old-fashioned.png");
        recipe.setGlassId(1L);
//        recipe.getRecipeIngredients();


        System.out.println(recipe);
        System.out.println(recipe.getRecipeIngredients());

        recipeService.save(recipe);
        return "redirect:/custom";
    }

    @GetMapping("/edit-cocktail/{id}")
    public String showFormForCocktailEdit(@PathVariable Long id, Model model) {
        System.out.println("We're in RecipeController showFormForCocktailEdit method");
        Recipe recipe = recipeService.findById(id);
        List<Ingredient> ingredients = ingredientRepository.findAllByOrderByNameAsc();

        System.out.println("Recipe " + recipe);
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);

        return "edit-cocktail-form";
    }
}






















