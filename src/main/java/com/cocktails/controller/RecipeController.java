package com.cocktails.controller;

import com.cocktails.dao.IngredientRepository;
import com.cocktails.dao.UnitRepository;
import com.cocktails.controller.model.RecipeDTO;
import com.cocktails.entity.*;
import com.cocktails.service.*;
import com.cocktails.service.mapper.RecipeMapper;
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
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.*;

@Controller
@RequestMapping
public class RecipeController {

    private static final String LIST_OF_INGREDIENT_NAMES = "listOfIngredientNames";
    private static final String FAVOURITE_IDS = "favouriteIds";
    private static final String RECIPES = "recipes";
    private static final String RECIPE = "recipe";
    private static final String RECIPE_DTO = "recipeDTO";
    private static final String INGREDIENTS = "ingredients";
    private static final String UNITS = "units";
    public static final String REDIRECT = "redirect:/";
    public static final String CUSTOM = "custom";
    private RecipeService recipeService;
    private UserService userService;
    private IngredientRepository ingredientRepository;
    private IngredientService ingredientService;
    private RecipeIngredientService recipeIngredientService;
    private UnitRepository unitRepository;
    private RecipeMapper recipeMapper;

    @Autowired
    public RecipeController(RecipeService recipeService,
                            UserService userService,
                            IngredientRepository ingredientRepository,
                            IngredientService ingredientService,
                            RecipeIngredientService recipeIngredientService,
                            UnitRepository unitRepository,
                            RecipeMapper recipeMapper) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.ingredientRepository = ingredientRepository;
        this.ingredientService = ingredientService;
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

    @GetMapping("/recipes")
    public String findRecipes(@RequestParam(required = false) String searchedRecipeName,
                              @RequestParam(required = false) String searchedIngredientName,
                              Principal currentUser,
                              Model model) {

        List<Recipe> recipes = new ArrayList<>();
        List<String> listOfIngredientNames = ingredientService.getListOfIngredientNames();
        model.addAttribute(LIST_OF_INGREDIENT_NAMES, listOfIngredientNames);
        addUserFavouritesToModel(currentUser, model);

//            Optional<String> searchedIngredientName

//            searchedIngredientName.ifPresent(name -> {
//                Ingredient selectedIngredient = ingredientService.findByName(name);
//                List<Recipe> recipeWithSearchedIngredient = recipeService.findAll().stream()
//                        .filter(recipe -> recipe.getRecipeIngredients().stream()
//                                .anyMatch(isPartOfRecipe(selectedIngredient)))
//                        .collect(Collectors.toList());
//            })

            if (searchedIngredientName != null) {
                Ingredient selectedIngredient = ingredientService.findByName(searchedIngredientName);
                recipes = recipeService.findAll().stream()
                        .filter(recipe -> recipe.getRecipeIngredients().stream()
                                .anyMatch(isPartOfRecipe(selectedIngredient)))
                        .collect(Collectors.toList());

//                Ingredient selectedIngredient = ingredientService.findByName(searchedIngredientName);
//                for (Recipe recipe : recipeService.findAll()) {
//                    for (RecipeIngredient ri : recipe.getRecipeIngredients()) {
//                        if (ri.getIngredients().getIngredientId().equals(selectedIngredient.getIngredientId())) {
//                            recipes.add(recipe);
//                        }
//                    }
//                }
            }
            // objecy is null jak wczesniej
            else if (searchedRecipeName == null) {
                recipes = recipeService.findAll();
            }
            else {
                recipes = recipeService.findByNameContaining(searchedRecipeName);
            }
            model.addAttribute(RECIPES, recipes);
            return RECIPES;
    }

    @GetMapping("/recipes/{id}")
    public String getRecipe(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute(RECIPE, recipe);
        return "recipe-detail";
    }

    @PostMapping("/addToFavourites/{id}")
    public String toggleToFavourites(@PathVariable Long id) {
        User user = getAuthenticatedUser();
        Recipe recipe = recipeService.findById(id);
        userService.toggleToFavourites(recipe, user);
        return "redirect:/recipes";
    }

    @GetMapping("/favourites")
    public String getFavourites(Model model) {
        User user = getAuthenticatedUser();
        Set<Recipe> recipes = user.getFavouriteRecipes();
        model.addAttribute(RECIPES, recipes);
        return "favourites";
    }

    @GetMapping("/custom")
    public String getCustomRecipes (Model model) {
        User user = getAuthenticatedUser();
        Set<Recipe> recipes = user.getCustomRecipes();
        model.addAttribute(RECIPES, recipes);
        return CUSTOM;
    }

    @GetMapping("/create-cocktail")
    public String showFormForCocktailCreation(Model model) {
        RecipeDTO recipeDTO = new RecipeDTO();
        List<Ingredient> ingredients = ingredientRepository.findAllByOrderByNameAsc();
        List<Unit> units = unitRepository.findAllByOrderByNameAsc();
        model.addAttribute(RECIPE_DTO, recipeDTO);
        model.addAttribute(INGREDIENTS, ingredients);
        model.addAttribute(UNITS, units);
        return "cocktail-form";
    }

    @PostMapping("/save-cocktail")
    public String saveCocktail(@ModelAttribute(RECIPE) RecipeDTO recipeDTO) {
        User user = getAuthenticatedUser();
        Recipe recipe = recipeMapper.toRecipe(recipeDTO); // przeniesc mappera do recipeService
        recipeService.save(recipe);
        userService.addToCustom(recipe, user);
        return REDIRECT + CUSTOM;
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

    private List<Long> getFavouriteRecipeIds() {
        return getAuthenticatedUser().getFavouriteRecipes().stream()
                .map(recipe -> recipe.getRecipeId()).collect(Collectors.toList());
        // poczytać o reference methods
    }
    private User getAuthenticatedUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userService.findById(userDetails.getId());
    }

    private void addUserFavouritesToModel(Principal currentUser, Model model) {
        if (!isNull(currentUser)) {
            model.addAttribute(FAVOURITE_IDS, getFavouriteRecipeIds());
        }
    }

    private Predicate<RecipeIngredient> isPartOfRecipe(Ingredient selectedIngredient) {
        return it -> it.getIngredients().getIngredientId().equals(selectedIngredient.getIngredientId());
    }
}






















