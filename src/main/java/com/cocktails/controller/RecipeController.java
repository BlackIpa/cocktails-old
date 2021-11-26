package com.cocktails.controller;

import com.cocktails.dao.IngredientRepository;
import com.cocktails.dao.UnitRepository;
import com.cocktails.dto.RecipeDTO;
import com.cocktails.entity.*;
import com.cocktails.service.RecipeIngredientService;
import com.cocktails.service.RecipeMapper;
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
    private RecipeIngredientService recipeIngredientService;
    private UnitRepository unitRepository;
    private List<Long> userFavouriteRecipes = new ArrayList<Long>();
    private RecipeMapper recipeMapper;

    @Autowired
    public RecipeController(RecipeService recipeService,
                            UserService userService,
                            IngredientRepository ingredientRepository,
                            RecipeIngredientService recipeIngredientService,
                            UnitRepository unitRepository,
                            RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientService = recipeIngredientService;
        this.unitRepository = unitRepository;
        this.recipeMapper = recipeMapper;
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
        User user = getAuthenticatedUser();

        for (Recipe rec : user.getFavouriteRecipes()) {
            System.out.println(rec.getName());
            list.add(rec.getRecipeId());
        }
        return list;
    }

    @PostMapping("/addToFavourites/{id}")
    public String toggleToFavourites(@PathVariable Long id) {
        System.out.println("We're in UserController addToFavourites method");

        User user = getAuthenticatedUser();
        Recipe recipe = recipeService.findById(id);

        userService.toggleToFavourites(recipe, user);

        return "redirect:/recipes";
    }

    @GetMapping("/favourites")
    public String getFavourites(Model model) {
        System.out.println("We're in RecipeController getFavourites method");
        User user = getAuthenticatedUser();
        Set<Recipe> recipes = user.getFavouriteRecipes();

        model.addAttribute("recipes", recipes);

        return "favourites";
    }

    @GetMapping("/custom")
    public String getCustomRecipes (Model model) {
        System.out.println("We're in RecipeController getCustomRecipes method");
        User user = getAuthenticatedUser();

        Set<Recipe> recipes = user.getCustomRecipes();
        model.addAttribute("recipes", recipes);

        System.out.println("We're in: RecipeRestController getCustomRecipes method");
        return "custom";
    }

    @GetMapping("/create-cocktail")
    public String showFormForCocktailCreation(Model model) {
        System.out.println("We're in RecipeController showFormForCocktailCreation method");

        User user = getAuthenticatedUser();
        System.out.println("User email: " + user.getEmail());

        RecipeDTO recipeDTO = new RecipeDTO();
        List<Ingredient> ingredients = ingredientRepository.findAllByOrderByNameAsc();
        List<Unit> units = unitRepository.findAllByOrderByNameAsc();

        model.addAttribute("recipeDTO", recipeDTO);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("units", units);

        return "cocktail-form";
    }


    @PostMapping("/save-cocktail")
    public String saveCocktail(@ModelAttribute("recipe") RecipeDTO recipeDTO) {
        System.out.println("We're in RecipeController saveCocktail method");

        User user = getAuthenticatedUser();

        Recipe recipe = recipeMapper.toRecipe(recipeDTO);

        System.out.println(recipe);

        recipeService.save(recipe);

        userService.addToCustom(recipe, user);

        return "redirect:/custom";
    }

//    @GetMapping("/edit-cocktail/{id}")
//    public String showFormForCocktailEdit(@PathVariable Long id, Model model) {
//        System.out.println("We're in RecipeController showFormForCocktailEdit method");
//        Recipe recipe = recipeService.findById(id);
//        List<Ingredient> ingredients = ingredientRepository.findAllByOrderByNameAsc();
//
//        System.out.println("Recipe " + recipe);
//        model.addAttribute("recipe", recipe);
//        model.addAttribute("ingredients", ingredients);
//
//        return "edit-cocktail-form";
//    }

    private User getAuthenticatedUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userService.findById(userDetails.getId());
    }
}






















